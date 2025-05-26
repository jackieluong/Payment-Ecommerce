package com.GeekUp.Shop.service.order;

import com.GeekUp.Shop.dto.request.CreateOrderRequest;
import com.GeekUp.Shop.dto.response.BaseOrderResponse;
import com.GeekUp.Shop.entity.Order;

public interface IOrderService {

    BaseOrderResponse createOrder(CreateOrderRequest createOrderRequest);

    void markOrderAsPaid(Long orderId);

    Order findOrderWithItemsById(Long orderId);

}
