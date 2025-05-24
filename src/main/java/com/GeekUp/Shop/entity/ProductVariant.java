package com.GeekUp.Shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_variant")
@Getter
@Setter
@NoArgsConstructor
public class ProductVariant {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "variant_seq")
    @SequenceGenerator(name = "variant_seq", sequenceName = "variant_seq", allocationSize = 50)
    private long id;

    private String sku;

    private int stock;

    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productVariant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariantOption> productVariantOptions = new ArrayList<>();

    public ProductVariant(Long id){
        this.id = id;
    }
}
