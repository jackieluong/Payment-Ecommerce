package com.Payment.Shop.controller;

import com.Payment.Shop.dto.ResultObject;
import com.Payment.Shop.dto.request.CreateProductRequest;
import com.Payment.Shop.dto.response.BaseProductResponse;
import com.Payment.Shop.service.product.IProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<ResultObject> createCompany(@Valid @RequestBody CreateProductRequest createProductRequest) {


        BaseProductResponse productResponse = productService.createProduct(createProductRequest);

        ResultObject<BaseProductResponse> result = new ResultObject<>(true, "Register successfully", HttpStatus.CREATED, productResponse);

        return new ResponseEntity<>(result, HttpStatus.CREATED);

    }

}
