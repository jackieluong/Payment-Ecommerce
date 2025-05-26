package com.GeekUp.Shop.controller;


import com.GeekUp.Shop.constant.PaymentMethod;
import com.GeekUp.Shop.dto.ResultObject;
import com.GeekUp.Shop.dto.request.PaymentRequest;
import com.GeekUp.Shop.dto.response.BasePaymentResponse;
import com.GeekUp.Shop.dto.response.IpnResponse;
import com.GeekUp.Shop.service.payment.IpnHandlerFactory;
import com.GeekUp.Shop.service.payment.PaymentHandlerContext;
import com.GeekUp.Shop.service.payment.IpnHandler;
import com.GeekUp.Shop.util.RequestUtil;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
@Slf4j
public class PaymentController {

    private final PaymentHandlerContext paymentHandler;
    private final IpnHandlerFactory ipnHandlerFactory;

    @Value("${app.frontend-url}")
    private String frontendUrl;

    public PaymentController(PaymentHandlerContext paymentHandler, IpnHandlerFactory ipnHandlerFactory) {
        this.paymentHandler = paymentHandler;
        this.ipnHandlerFactory = ipnHandlerFactory;
    }


    @PostMapping
    public ResponseEntity<ResultObject> createPayment(@Valid @RequestBody PaymentRequest paymentRequest, HttpServletRequest httpServletRequest) {


        paymentRequest.setIpAddress(RequestUtil.getIpAddress(httpServletRequest));

        BasePaymentResponse paymentResponse = paymentHandler.executePayment(paymentRequest);

        ResultObject result = new ResultObject<>(true, "Create payment url successfully", HttpStatus.OK, paymentResponse);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/vnpay_ipn")
    IpnResponse processIpn(@RequestParam Map<String, String> params) {
        log.info("[VNPay Ipn] Params: {}", params);

        return ipnHandlerFactory.processIpn(PaymentMethod.VNPAY, params);
    }

    @GetMapping("/stripe-callback")
    void processStripeSuccess(@RequestParam Map<String, String> params, HttpServletRequest request, HttpServletResponse response) {
        log.info("[Stripe] Params: {}", params);

        try{
            Boolean success = ipnHandlerFactory.processIpn(PaymentMethod.STRIPE, params);

            if(success.equals(Boolean.TRUE)){
                response.sendRedirect(frontendUrl + "/payment?success=true");
            }else {
                response.sendRedirect(frontendUrl + "/payment?success=false");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
