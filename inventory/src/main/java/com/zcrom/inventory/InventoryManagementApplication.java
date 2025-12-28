package com.zcrom.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryManagementApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementApplication.class, args);
        System.out.println("Inventory Management System is running on http://localhost:8080");
        System.out.println("API Documentation:");
        System.out.println("GET  http://localhost:8080/api/products");
        System.out.println("POST http://localhost:8080/api/products");
        System.out.println("POST http://localhost:8080/api/auth/login");
    }
}