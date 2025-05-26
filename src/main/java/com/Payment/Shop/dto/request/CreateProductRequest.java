package com.Payment.Shop.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateProductRequest {
    private String name;
    private String description;
    private double basePrice;
    private double discountPercent;
    private String imageUrl;
    private Long categoryId;
    private List<CreateProductVariantRequest> variants;
}
