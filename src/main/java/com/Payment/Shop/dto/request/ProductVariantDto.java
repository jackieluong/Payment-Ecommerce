package com.Payment.Shop.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductVariantDto {
    private Long variantId;
    private Integer quantity;
}
