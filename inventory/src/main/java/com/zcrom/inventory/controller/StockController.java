// src/main/java/com/zcrom/inventory/controller/StockController.java
package com.zcrom.inventory.controller;

import com.zcrom.inventory.entity.StockTransaction;
import com.zcrom.inventory.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stock")
@CrossOrigin(origins = "http://localhost:3000")
public class StockController {
    
    @Autowired
    private StockService stockService;
    
    @PostMapping("/in")
    public ResponseEntity<?> stockIn(@RequestBody Map<String, Object> request) {
        try {
            Long productId = Long.valueOf(request.get("productId").toString());
            Integer quantity = Integer.valueOf(request.get("quantity").toString());
            String notes = (String) request.get("notes");
            
            StockTransaction transaction = stockService.stockIn(productId, quantity, notes);
            return ResponseEntity.ok(transaction);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping("/out")
    public ResponseEntity<?> stockOut(@RequestBody Map<String, Object> request) {
        try {
            Long productId = Long.valueOf(request.get("productId").toString());
            Integer quantity = Integer.valueOf(request.get("quantity").toString());
            String notes = (String) request.get("notes");
            
            StockTransaction transaction = stockService.stockOut(productId, quantity, notes);
            return ResponseEntity.ok(transaction);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @GetMapping("/transactions")
    public List<StockTransaction> getAllTransactions() {
        return stockService.getAllTransactions();
    }
    
    @GetMapping("/transactions/product/{productId}")
    public List<StockTransaction> getProductTransactions(@PathVariable Long productId) {
        return stockService.getTransactionHistory(productId);
    }
    
    @GetMapping("/report")
    public Map<String, Object> getStockMovementReport() {
        return stockService.getStockMovementReport();
    }
}