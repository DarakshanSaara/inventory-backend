package com.zcrom.inventory.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {
    
    @GetMapping("/public")
    public String publicEndpoint() {
        return "Public endpoint is working!";
    }
    
    @GetMapping("/secure")
    public String secureEndpoint() {
        return "Secure endpoint is working!";
    }
}