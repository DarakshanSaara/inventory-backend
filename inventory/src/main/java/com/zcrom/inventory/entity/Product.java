package com.zcrom.inventory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String productId;
    
    @Column(nullable = false)
    private String name;
    
    private String category;
    private String unit;
    private Double price;
    
    @Column(name = "min_stock_level")
    private Integer minStockLevel;
    
    @Column(name = "current_stock")
    private Integer currentStock = 0;
    
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    // Helper method to generate product ID
    public void generateProductId() {
        if (this.productId == null) {
            String prefix = "";
            if (category != null) {
                switch(category.toUpperCase()) {
                    case "ELECTRONICS": prefix = "ELEC"; break;
                    case "HARDWARE": prefix = "HARD"; break;
                    case "RAW MATERIAL": prefix = "RAW"; break;
                    default: prefix = "PROD";
                }
            } else {
                prefix = "PROD";
            }
            this.productId = prefix + System.currentTimeMillis() % 10000;
        }
    }
}