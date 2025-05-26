package com.Payment.Shop.repository;

import com.Payment.Shop.entity.ProductVariantOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantOptionRepository extends JpaRepository<ProductVariantOption, Long> {
}
