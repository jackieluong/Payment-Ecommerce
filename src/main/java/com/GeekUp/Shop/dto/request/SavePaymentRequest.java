package com.GeekUp.Shop.dto.request;

import com.GeekUp.Shop.constant.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavePaymentRequest extends PaymentRequest{

    // transactionId is used for VNPay payment, crucial to query the transaction
    private String transactionId;

    // sessionId is used for Stripe payment, crucial to retrieve checkout session
    private String sessionId;

}
