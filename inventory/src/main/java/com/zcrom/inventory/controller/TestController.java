package com.zcrom.inventory.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/test", "/api/test"})  // Handle both paths
public class TestController {
    
    @GetMapping("/public")
    public String publicEndpoint() {
        return "Public endpoint is working! API Version: 1.0";
    }
    
    @GetMapping("/secure")
    public String secureEndpoint() {
        return "Secure endpoint is working!";
    }
}