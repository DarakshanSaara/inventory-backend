package com.zcrom.inventory.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    private String role; // ADMIN, MANAGER, STAFF
    
    // For quick setup, add a static method to create default admin
    public static User createDefaultAdmin() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin123"); // We'll encode this later
        admin.setRole("ADMIN");
        return admin;
    }

    public void setEmail(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEmail'");
    }

    public void setFullName(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFullName'");
    }
}