package com.GeekUp.Shop.service.payment;

import com.GeekUp.Shop.constant.PaymentMethod;
import com.GeekUp.Shop.dto.response.IpnResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class IpnHandlerFactory {
    private final IpnHandler<IpnResponse> vnPayIpnHandler;
    private final IpnHandler<Boolean> stripeIpnHandler;

    public IpnHandlerFactory(IpnHandler<IpnResponse> vnPayIpnHandler, IpnHandler<Boolean> stripeIpnHandler) {
        this.vnPayIpnHandler = vnPayIpnHandler;
        this.stripeIpnHandler = stripeIpnHandler;
    }

    public <T> IpnHandler<T> getIpnHandler(PaymentMethod paymentMethod) {
        return switch (paymentMethod) {
            case VNPAY -> (IpnHandler<T>) vnPayIpnHandler;
            case STRIPE -> (IpnHandler<T>) stripeIpnHandler;
            default -> throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        };
    }


    public <T> T processIpn(PaymentMethod method, Map<String, String> params) {
        IpnHandler<T> handler = getIpnHandler(method);
        return handler.processIPN(params);
    }

    public IpnHandler<IpnResponse> getVnPayIpnHandler() {
        return vnPayIpnHandler;
    }

    public IpnHandler<Boolean> getStripeIpnHandler() {
        return stripeIpnHandler;
    }
}
