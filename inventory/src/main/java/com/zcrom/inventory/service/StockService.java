// src/main/java/com/zcrom/inventory/service/StockService.java
package com.zcrom.inventory.service;

import com.zcrom.inventory.entity.Product;
import com.zcrom.inventory.entity.StockTransaction;
import com.zcrom.inventory.repository.ProductRepository;
import com.zcrom.inventory.repository.StockTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class StockService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private StockTransactionRepository stockTransactionRepository;
    
    @Transactional
    public StockTransaction stockIn(Long productId, Integer quantity, String notes) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        
        // Update product stock
        int newStock = product.getCurrentStock() + quantity;
        product.setCurrentStock(newStock);
        productRepository.save(product);
        
        // Create transaction record
        StockTransaction transaction = new StockTransaction();
        transaction.setProduct(product);
        transaction.setType(StockTransaction.TransactionType.IN);
        transaction.setQuantity(quantity);
        transaction.setNotes(notes);
        
        return stockTransactionRepository.save(transaction);
    }
    
    @Transactional
    public StockTransaction stockOut(Long productId, Integer quantity, String notes) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        
        // Check if enough stock
        if (product.getCurrentStock() < quantity) {
            throw new RuntimeException("Insufficient stock. Available: " + product.getCurrentStock());
        }
        
        // Update product stock
        int newStock = product.getCurrentStock() - quantity;
        product.setCurrentStock(newStock);
        productRepository.save(product);
        
        // Create transaction record
        StockTransaction transaction = new StockTransaction();
        transaction.setProduct(product);
        transaction.setType(StockTransaction.TransactionType.OUT);
        transaction.setQuantity(quantity);
        transaction.setNotes(notes);
        
        return stockTransactionRepository.save(transaction);
    }
    
    public List<StockTransaction> getTransactionHistory(Long productId) {
        return stockTransactionRepository.findByProductId(productId);
    }
    
    public List<StockTransaction> getAllTransactions() {
        return stockTransactionRepository.findAll();
    }
    
    public Map<String, Object> getStockMovementReport() {
        List<StockTransaction> allTransactions = stockTransactionRepository.findAll();
        
        int totalStockIn = allTransactions.stream()
            .filter(t -> t.getType() == StockTransaction.TransactionType.IN)
            .mapToInt(StockTransaction::getQuantity)
            .sum();
            
        int totalStockOut = allTransactions.stream()
            .filter(t -> t.getType() == StockTransaction.TransactionType.OUT)
            .mapToInt(StockTransaction::getQuantity)
            .sum();
            
        return Map.of(
            "totalStockIn", totalStockIn,
            "totalStockOut", totalStockOut,
            "netMovement", totalStockIn - totalStockOut,
            "totalTransactions", allTransactions.size()
        );
    }
}