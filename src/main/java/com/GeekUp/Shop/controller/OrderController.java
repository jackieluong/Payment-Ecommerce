package com.GeekUp.Shop.controller;

import com.GeekUp.Shop.dto.ResultObject;
import com.GeekUp.Shop.dto.request.CreateOrderRequest;
import com.GeekUp.Shop.dto.request.CreateProductRequest;
import com.GeekUp.Shop.dto.response.BaseOrderResponse;
import com.GeekUp.Shop.dto.response.BaseProductResponse;
import com.GeekUp.Shop.service.IOrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    public ResponseEntity<ResultObject> createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {

        BaseOrderResponse orderResponse = orderService.createOrder(createOrderRequest);

        ResultObject result = new ResultObject<>(true, "Create order successfully", HttpStatus.CREATED, orderResponse);

        return new ResponseEntity<>(result, HttpStatus.CREATED);

    }
}
