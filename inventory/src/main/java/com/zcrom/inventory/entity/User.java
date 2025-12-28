package com.zcrom.inventory.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(unique = true, nullable = false)
    private String username;
    
    @NotBlank
    @Column(nullable = false)
    private String password;
    
    @NotBlank
    private String role = "USER"; // ADMIN, MANAGER, STAFF
    
    @Email
    private String email;
    
    private String fullName;
    
    private Boolean active = true;
    
    // For quick setup, add a static method to create default admin
    public static User createDefaultAdmin(PasswordEncoder passwordEncoder) {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole("ADMIN");
        admin.setEmail("admin@inventory.com");
        admin.setFullName("Administrator");
        admin.setActive(true);
        return admin;
    }
}