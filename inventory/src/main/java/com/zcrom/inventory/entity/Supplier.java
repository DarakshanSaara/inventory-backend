// src/main/java/com/zcrom/inventory/entity/Supplier.java
package com.zcrom.inventory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "suppliers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String supplierId;
    
    @Column(nullable = false)
    private String name;
    
    private String phone;
    private String email;
    private String address;
    
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    // Auto-generate supplier ID
    public void generateSupplierId() {
        if (this.supplierId == null) {
            String prefix = "SUP";
            String timestamp = String.valueOf(System.currentTimeMillis() % 10000);
            this.supplierId = prefix + timestamp;
        }
    }
}