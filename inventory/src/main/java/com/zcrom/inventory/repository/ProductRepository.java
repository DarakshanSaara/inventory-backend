package com.zcrom.inventory.repository;

import com.zcrom.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductId(String productId);
    List<Product> findByCategory(String category);
    List<Product> findByCurrentStockLessThan(Integer stockLevel);
}