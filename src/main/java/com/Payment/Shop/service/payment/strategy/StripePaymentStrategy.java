package com.Payment.Shop.service.payment.strategy;

import com.Payment.Shop.config.PaymentConfig.StripeConfig;
import com.Payment.Shop.constant.PaymentStatus;
import com.Payment.Shop.dto.request.PaymentRequest;
import com.Payment.Shop.dto.response.BasePaymentResponse;
import com.Payment.Shop.dto.response.StripePaymentResponse;
import com.Payment.Shop.entity.Order;
import com.Payment.Shop.exception.PaymentException;
import com.Payment.Shop.service.order.IOrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StripePaymentStrategy extends PaymentStrategy {

    private IOrderService orderService;

    public StripePaymentStrategy(IOrderService orderService) {
        super();
        this.orderService = orderService;
    }


    @Override
    public BasePaymentResponse processPayment(PaymentRequest paymentRequest) throws PaymentException {

        Order order = orderService.findOrderWithItemsById(paymentRequest.getOrderId());

        if (order.getPaymentStatus().equals(PaymentStatus.PAYMENT_COMPLETED)){
            throw new PaymentException("Payment already completed");
        }

        List<SessionCreateParams.LineItem> lineItems = order.getOrderItems().stream().map(orderItem -> {

                    SessionCreateParams.LineItem.PriceData.ProductData productData= SessionCreateParams.LineItem.PriceData.ProductData.builder()
                            .setName(orderItem.getProductVariant().getProduct().getName())
                            .setDescription(orderItem.getProductVariant().getProduct().getDescription())
                            .build();

                    SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData.builder()
                            .setCurrency(StripeConfig.currency)
                            .setProductData(productData)
                            .setUnitAmount((long) (orderItem.getProductVariant().getPrice()))
                            .build();

                    SessionCreateParams.LineItem lineItem = new SessionCreateParams.LineItem.Builder()
                            .setPriceData(priceData)
                            .setQuantity((long) orderItem.getQuantity())
                            .build();

                    return lineItem;

                }
        ).collect(Collectors.toList());

        SessionCreateParams params = SessionCreateParams.builder()
                .addAllLineItem(lineItems)
                .setClientReferenceId(String.valueOf(order.getId()))
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(StripeConfig.successUrl)
                .setCancelUrl(StripeConfig.cancelUrl)
                .setCurrency("vnd")
                .build();



        try{
            Session session = Session.create(params);
            return StripePaymentResponse.builder()
                    .status("SUCCESS")
                    .message("Successfully create payment url")
                    .sessionId(session.getId())
                    .sessionUrl(session.getUrl())
                    .build() ;
        }catch (StripeException e){
//            log.error("Stripe exception: {}", e.getMessage());
            throw new PaymentException(e);

        }




    }

}
