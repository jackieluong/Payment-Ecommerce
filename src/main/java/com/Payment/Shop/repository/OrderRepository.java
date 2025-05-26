package com.Payment.Shop.repository;

import com.Payment.Shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o LEFT JOIN " +
            "FETCH o.orderItems i LEFT JOIN FETCH i.productVariant v " +
            "JOIN FETCH v.product " +
            "WHERE o.id = :orderId")
    Optional<Order> findOrderByIdWithItems(@Param("orderId") Long orderId);
}
