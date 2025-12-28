// src/main/java/com/zcrom/inventory/repository/SupplierRepository.java
package com.zcrom.inventory.repository;

import com.zcrom.inventory.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findBySupplierId(String supplierId);
    Optional<Supplier> findByName(String name);
}