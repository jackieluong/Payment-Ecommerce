package com.Payment.Shop.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StripeSessionPaymentStatus {

    PAID("paid"),
    UNPAID("unpaid"),
    NO_PAYMENT_REQUIRED("no_payment_required"),
    ;
    private final String value;
}
