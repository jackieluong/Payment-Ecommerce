package com.Payment.Shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductVariantOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "variant_option_seq")
//    @SequenceGenerator(name = "variant_option_seq", sequenceName = "variant_option_seq", allocationSize = 50)
//    private long id;

    private String attribute;

    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_variant_id")
    private ProductVariant productVariant;

    public ProductVariantOption(String attribute, String value){
        this.attribute = attribute;
        this.value = value;
    }

    public ProductVariantOption() {

    }
}
