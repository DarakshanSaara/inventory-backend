// src/main/java/com/zcrom/inventory/entity/StockTransaction.java
package com.zcrom.inventory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockTransaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type; // IN or OUT
    
    @Column(nullable = false)
    private Integer quantity;
    
    private String notes;
    
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime transactionDate;
    
    public enum TransactionType {
        IN, OUT
    }
}