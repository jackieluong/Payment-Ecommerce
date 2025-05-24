package com.GeekUp.Shop.service;

import com.GeekUp.Shop.dto.request.CreateOrderRequest;
import com.GeekUp.Shop.dto.response.BaseOrderResponse;
import com.GeekUp.Shop.entity.Order;

import java.util.List;

public interface IOrderService {

    BaseOrderResponse createOrder(CreateOrderRequest createOrderRequest);

    void markOrderAsPaid(Long orderId);

    Order findOrderWithItemsById(Long orderId);

}
