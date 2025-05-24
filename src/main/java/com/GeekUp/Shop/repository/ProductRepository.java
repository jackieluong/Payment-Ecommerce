package com.GeekUp.Shop.repository;

import com.GeekUp.Shop.entity.Product;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
