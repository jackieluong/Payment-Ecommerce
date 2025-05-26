package com.Payment.Shop.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StripePaymentResponse extends BasePaymentResponse{
    private String status;

    private String message;

    private String sessionId;

    private String sessionUrl;
}
