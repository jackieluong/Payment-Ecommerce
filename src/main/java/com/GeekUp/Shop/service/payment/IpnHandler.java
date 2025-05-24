package com.GeekUp.Shop.service.payment;

import com.GeekUp.Shop.dto.response.IpnResponse;

import java.util.Map;

public interface IpnHandler {
    IpnResponse process(Map<String, String> params);
}
