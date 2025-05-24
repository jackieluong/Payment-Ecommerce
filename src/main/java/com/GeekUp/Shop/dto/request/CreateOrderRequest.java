package com.GeekUp.Shop.dto.request;

import com.GeekUp.Shop.constant.PaymentMethod;
import com.GeekUp.Shop.entity.Address;
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
