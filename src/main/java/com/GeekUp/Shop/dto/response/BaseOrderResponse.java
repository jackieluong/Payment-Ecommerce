package com.GeekUp.Shop.dto.response;

import com.GeekUp.Shop.constant.PaymentMethod;
import com.GeekUp.Shop.entity.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseOrderResponse {

    @JsonProperty("orderId")
    protected Long id;

    protected Address address;

    protected String note;

    protected String receiverName;

    protected String phone;

    protected PaymentMethod paymentMethod;

    protected double totalAmount;

}
