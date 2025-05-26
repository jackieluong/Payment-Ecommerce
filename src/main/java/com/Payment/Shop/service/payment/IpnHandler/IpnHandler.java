package com.Payment.Shop.service.payment.IpnHandler;

import java.util.Map;

public interface IpnHandler<T> {
    T processIPN(Map<String, String> params);


}
