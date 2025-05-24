package com.GeekUp.Shop.controller;


import com.GeekUp.Shop.dto.ResultObject;
import com.GeekUp.Shop.dto.request.PaymentRequest;
import com.GeekUp.Shop.dto.response.BasePaymentResponse;
import com.GeekUp.Shop.dto.response.IpnResponse;
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
    private final IpnHandler ipnHandler;

    @Value("${app.frontend-url}")
    private String frontendUrl;

    public PaymentController(PaymentHandlerContext paymentHandler, IpnHandler ipnHandler) {
        this.paymentHandler = paymentHandler;
        this.ipnHandler = ipnHandler;
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
        return ipnHandler.process(params);
    }

    @GetMapping("/stripe-callback")
    void processStripeSuccess(@RequestParam Map<String, String> params, HttpServletRequest request, HttpServletResponse response) {
        log.info("[Stripe] Params: {}", params);
        Session session = null;
        try {
            session = Session.retrieve(params.get("session_id"));
            response.sendRedirect(frontendUrl);
        }catch (StripeException exception){
            log.error("[Stripe] Exception: {}", exception.getMessage());
            throw new RuntimeException("Error retrieving session");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info("[Stripe] Session: {}", session);


    }
}
