package com.GeekUp.Shop.service.payment;


import com.GeekUp.Shop.constant.VNPayParams;
import com.GeekUp.Shop.constant.VnpIpnResponseConst;
import com.GeekUp.Shop.dto.response.IpnResponse;
import com.GeekUp.Shop.service.IOrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class VNPayIpnHandler implements IpnHandler {

    private final VNPayStrategy vnPayStrategy;
    private final IOrderService orderService;


    public IpnResponse process(Map<String, String> params) {
        if (!vnPayStrategy.verifyIpn(params)) {
            return VnpIpnResponseConst.SIGNATURE_FAILED;
        }

        IpnResponse response = null;
        var txnRef = params.get(VNPayParams.TXN_REF);
        try {
            var orderId = Long.parseLong(txnRef);
//            bookingService.markBooked(bookingId);
            var responseCode = params.get(VNPayParams.RESPONSE_CODE);
            if (responseCode.equals("00")) {
                orderService.markOrderAsPaid(orderId);
                response = VnpIpnResponseConst.SUCCESS;
            }

        }
        catch (IllegalArgumentException e) {
            response = VnpIpnResponseConst.ORDER_NOT_FOUND;
        }
        catch (Exception e) {
            response = VnpIpnResponseConst.UNKNOWN_ERROR;
        }

        log.info("[VNPay Ipn] txnRef: {}, response: {}", txnRef, response);
        return response;
    }
}
