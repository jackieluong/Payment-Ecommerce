package com.GeekUp.Shop.repository;

import com.GeekUp.Shop.entity.ProductVariantOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantOptionRepository extends JpaRepository<ProductVariantOption, Long> {
}
