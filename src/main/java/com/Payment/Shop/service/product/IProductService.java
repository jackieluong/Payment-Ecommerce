package com.Payment.Shop.service.product;

import com.Payment.Shop.dto.request.CreateProductRequest;
import com.Payment.Shop.dto.response.BaseProductResponse;
import com.Payment.Shop.entity.ProductVariant;

import java.util.List;

public interface IProductService {

    BaseProductResponse createProduct(CreateProductRequest createProductRequest);

    List<ProductVariant> findAllProductVariantByVariantId(List<Long> variantIds);

    void saveAllProductVariant(List<ProductVariant> productVariants);
}
