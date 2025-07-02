package com.Payment.Shop.repository;

import com.Payment.Shop.entity.ProductVariant;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT pv FROM ProductVariant pv WHERE pv.id IN :variantIds")
    List<ProductVariant> findAllByIdWithLockIn(@Param("variantIds") List<Long> variantIds);

    @Modifying
    @Query("UPDATE ProductVariant pv SET pv.stock = pv.stock - :requestQuantity " +
            "WHERE pv.id = :id AND pv.stock >= :requestQuantity")
    int updateStockConditionally(@Param("id") Long id,
                                 @Param("requestQuantity") Integer requestQuantity);

}
