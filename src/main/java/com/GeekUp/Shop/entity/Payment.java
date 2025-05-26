package com.GeekUp.Shop.entity;

import com.GeekUp.Shop.constant.PaymentMethod;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "payments")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    private long id;

    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    private double amount;

    private String transactionId;

    private String sessionId;

    private Instant createdAt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Order order;
}
