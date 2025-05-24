package com.GeekUp.Shop.service.payment;

import com.GeekUp.Shop.dto.request.PaymentRequest;
import com.GeekUp.Shop.dto.response.BasePaymentResponse;
import com.GeekUp.Shop.exception.PaymentException;

// Interface for Payment Strategy
public interface IPaymentStrategy {
    BasePaymentResponse processPayment(PaymentRequest paymentRequest) throws PaymentException;
}
