package com.Payment.Shop.dto.request;

import com.Payment.Shop.constant.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

    @NotNull(message = "Order Id is required")
    protected Long orderId;

    protected double totalAmount;

    @NotNull(message = "Payment method is required")
    protected PaymentMethod paymentMethod;

    protected String ipAddress;


}
