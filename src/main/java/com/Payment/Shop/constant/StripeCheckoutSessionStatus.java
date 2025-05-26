package com.Payment.Shop.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StripeCheckoutSessionStatus {

    OPEN("open"),
    COMPLETE("complete"),
    EXPIRED("expired")
    ;

    private final String value;
}
