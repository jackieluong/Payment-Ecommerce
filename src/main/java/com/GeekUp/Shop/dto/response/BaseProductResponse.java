package com.GeekUp.Shop.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class BaseProductResponse {
    protected long id;

    protected String name;

    protected String description;

    protected double basePrice;

    protected double discountPercent;

    protected String imageUrl;

    protected Instant createdAt;

    protected Instant updatedAt;
}
