// src/main/java/com/zcrom/inventory/repository/StockTransactionRepository.java
package com.zcrom.inventory.repository;

import com.zcrom.inventory.entity.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long> {
    List<StockTransaction> findByProductId(Long productId);
    List<StockTransaction> findByTransactionDateBetween(LocalDateTime start, LocalDateTime end);
    List<StockTransaction> findByType(StockTransaction.TransactionType type);
}