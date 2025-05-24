package com.GeekUp.Shop.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class VNPaymentRequest extends PaymentRequest{
    protected double totalAmount;
}
