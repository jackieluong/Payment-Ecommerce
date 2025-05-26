package com.Payment.Shop.controller;

import com.Payment.Shop.dto.ResultObject;
import com.Payment.Shop.dto.request.CreateOrderRequest;
import com.Payment.Shop.dto.response.BaseOrderResponse;
import com.Payment.Shop.service.order.IOrderService;
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
