package com.GeekUp.Shop.entity;

import com.GeekUp.Shop.constant.PaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment {

    @Id
    private long id;

    private PaymentMethod paymentMethod;

    private double amount;

    private String transactionId;

    private Instant createdAt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Order order;
}
