package com.GeekUp.Shop.service;

import com.GeekUp.Shop.dto.request.CreateProductRequest;
import com.GeekUp.Shop.dto.response.BaseProductResponse;
import com.GeekUp.Shop.entity.ProductVariant;

import java.util.List;
import java.util.Set;

public interface IProductService {

    BaseProductResponse createProduct(CreateProductRequest createProductRequest);

    List<ProductVariant> findAllProductVariantByVariantId(List<Long> variantIds);

    void saveAllProductVariant(List<ProductVariant> productVariants);
}
