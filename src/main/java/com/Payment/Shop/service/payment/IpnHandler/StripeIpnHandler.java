package com.Payment.Shop.service.payment.IpnHandler;

import com.Payment.Shop.constant.PaymentMethod;
import com.Payment.Shop.constant.StripeCheckoutSessionStatus;
import com.Payment.Shop.constant.StripeParams;
import com.Payment.Shop.constant.StripeSessionPaymentStatus;
import com.Payment.Shop.dto.request.SavePaymentRequest;
import com.Payment.Shop.service.order.IOrderService;
import com.Payment.Shop.service.payment.strategy.StripePaymentStrategy;
import com.stripe.model.checkout.Session;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class StripeIpnHandler implements IpnHandler<Boolean> {

    private final StripePaymentStrategy stripePaymentStrategy;
    private final IOrderService orderService;

    public StripeIpnHandler(StripePaymentStrategy stripePaymentStrategy, IOrderService orderService) {
        this.stripePaymentStrategy = stripePaymentStrategy;
        this.orderService = orderService;
    }


    private SavePaymentRequest buildSavePaymentRequest(Session session) {

        var orderId = Long.parseLong(session.getClientReferenceId());

        SavePaymentRequest savePaymentRequest = new SavePaymentRequest();
        savePaymentRequest.setOrderId(orderId);
        savePaymentRequest.setPaymentMethod(PaymentMethod.STRIPE);
        savePaymentRequest.setTotalAmount(session.getAmountTotal());
        savePaymentRequest.setSessionId(session.getId());
        savePaymentRequest.setCreatedAt(String.valueOf(session.getCreated()));
        return savePaymentRequest;

    }


    @Override
    @Transactional
    public Boolean processIPN(Map<String, String> params) {
        String sessionId = params.get(StripeParams.SESSION_ID);

        if (sessionId == null) {
            log.error("[Stripe IPN] No session_id provided in params");
            return false;
        }

        try{
            Session session = Session.retrieve(sessionId);
            log.info("[Stripe IPN] Session: {}", session);

            if(!session.getPaymentStatus().equals(StripeSessionPaymentStatus.PAID.getValue()) ||
            !session.getStatus().equals(StripeCheckoutSessionStatus.COMPLETE.getValue())) {
                return false;
            }
            String orderId = session.getClientReferenceId();

            // Mark order as paid
            orderService.markOrderAsPaid(Long.parseLong(orderId), PaymentMethod.STRIPE);

            // Save payment to database
            SavePaymentRequest savePaymentRequest = this.buildSavePaymentRequest(session);
            stripePaymentStrategy.savePayment(savePaymentRequest);

            log.info("[Stripe IPN] Payment processed successfully for order: {}", orderId);

            return true;

        } catch (Exception e) {
            log.error("[Stripe IPN] Error processing payment: {}", e.getMessage(), e);
            return false;
        }

    }
}
