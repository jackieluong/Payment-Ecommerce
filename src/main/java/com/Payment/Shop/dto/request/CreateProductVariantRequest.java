package com.Payment.Shop.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CreateProductVariantRequest {
    private String sku;
    private double price;
    private int stockQuantity;
    private Map<String, String> attributes; // e.g., size, color
}
