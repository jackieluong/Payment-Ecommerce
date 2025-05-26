package com.Payment.Shop.dto.request;

import com.Payment.Shop.constant.PaymentMethod;
import com.Payment.Shop.entity.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderRequest {

    private List<ProductVariantDto> productVariants;

    private Address address;

    private String note;

    private String receiverName;

    private String phone;

    private PaymentMethod paymentMethod;

    private double totalAmount;

    private List<Long> voucherIds;

    private double shippingFee;
//
//    @Getter
//    @Setter
//    public static class ProductVariantRequest {
//
//    }


}
