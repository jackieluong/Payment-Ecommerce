package com.GeekUp.Shop.repository;

import com.GeekUp.Shop.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
