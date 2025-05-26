package com.GeekUp.Shop.service.payment;

import com.GeekUp.Shop.dto.response.IpnResponse;
import com.stripe.model.checkout.Session;

import java.util.Map;

public interface IpnHandler<T> {
    T processIPN(Map<String, String> params);


}
