package com.Payment.Shop.service.order;

import com.Payment.Shop.constant.PaymentMethod;
import com.Payment.Shop.dto.request.CreateOrderRequest;
import com.Payment.Shop.dto.response.BaseOrderResponse;
import com.Payment.Shop.entity.Order;

public interface IOrderService {

    BaseOrderResponse createOrder(CreateOrderRequest createOrderRequest);

    void markOrderAsPaid(Long orderId, PaymentMethod paymentMethod);

    Order findOrderWithItemsById(Long orderId);

}
