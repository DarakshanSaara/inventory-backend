package com.zcrom.inventory.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")  // Allow all origins
public class TestController {
    
    @GetMapping("/test")
    public Map<String, String> test() {
        return Map.of("message", "Backend API is working!");
    }
    
    @GetMapping("/products/public")
    public List<Map<String, Object>> getPublicProducts() {
        return Arrays.asList(
            Map.of("id", 1, "name", "Test Product 1", "price", 99.99, "stock", 10),
            Map.of("id", 2, "name", "Test Product 2", "price", 49.99, "stock", 5)
        );
    }
    
    @GetMapping("/products/dashboard/public")
    public Map<String, Object> getPublicDashboardStats() {
        return Map.of(
            "totalProducts", 25,
            "totalValue", 15432.67,
            "lowStockCount", 4
        );
    }
}