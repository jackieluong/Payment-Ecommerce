package com.GeekUp.Shop.service.payment;

import com.GeekUp.Shop.constant.PaymentMethod;
import com.GeekUp.Shop.dto.request.PaymentRequest;
import com.GeekUp.Shop.dto.request.SavePaymentRequest;
import com.GeekUp.Shop.dto.response.BasePaymentResponse;
import com.GeekUp.Shop.entity.Order;
import com.GeekUp.Shop.entity.Payment;
import com.GeekUp.Shop.exception.PaymentException;
import com.GeekUp.Shop.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

// Abstract class for Payment Strategy

public abstract class IPaymentStrategy {

    protected PaymentRepository paymentRepository;


    @Autowired
    public void setPaymentRepository(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    abstract BasePaymentResponse processPayment(PaymentRequest paymentRequest) throws PaymentException;

    void savePayment(SavePaymentRequest paymentRequest) {

        Payment payment = Payment.builder()
                .paymentMethod(paymentRequest.getPaymentMethod())
                .order(new Order(paymentRequest.getOrderId()))
                .amount(paymentRequest.getTotalAmount())
                .build();

        if(paymentRequest.getPaymentMethod().equals(PaymentMethod.VNPAY)){
            payment.setTransactionId(paymentRequest.getTransactionId());
        }else if(paymentRequest.getPaymentMethod().equals(PaymentMethod.STRIPE)){
            payment.setSessionId(paymentRequest.getSessionId());
        }


        try{
            paymentRepository.save(payment);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
