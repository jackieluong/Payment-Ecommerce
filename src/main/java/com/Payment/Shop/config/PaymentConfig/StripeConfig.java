package com.Payment.Shop.config.PaymentConfig;

import com.Payment.Shop.constant.Currency;
import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class StripeConfig {

    public static final String successUrl = "http://localhost:8080/api/v1/payments/stripe-callback?session_id={CHECKOUT_SESSION_ID}";
    public static final String cancelUrl = "http://localhost:8080/api/v1/payments/stripe-callback?session_id={CHECKOUT_SESSION_ID}";
    public static String currency = Currency.VND.getValue();

    @Value("${payment.stripe.secret-key}")
    private String stripeSecretKey;


    @PostConstruct
    public void init() {
        System.out.println("stripeSecretKey: " + stripeSecretKey);
        Stripe.apiKey = stripeSecretKey;
    }

}
