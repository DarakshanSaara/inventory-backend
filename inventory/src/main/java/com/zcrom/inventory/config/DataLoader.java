package com.zcrom.inventory.config;

import com.zcrom.inventory.entity.User;
import com.zcrom.inventory.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create admin user if not exists
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");
            admin.setEmail("admin@inventory.com");
            admin.setFullName("Administrator");
            admin.setActive(true);
            userRepository.save(admin);
            
            logger.info("✅ Admin user created successfully!");
            logger.info("   Username: admin");
            logger.info("   Password: admin123");
            logger.info("   Email: admin@inventory.com");
        } else {
            logger.info("✅ Admin user already exists");
        }

        // Optional: Create manager user
        if (userRepository.findByUsername("manager").isEmpty()) {
            User manager = new User();
            manager.setUsername("manager");
            manager.setPassword(passwordEncoder.encode("manager123"));
            manager.setRole("MANAGER");
            manager.setEmail("manager@inventory.com");
            manager.setFullName("Inventory Manager");
            manager.setActive(true);
            userRepository.save(manager);
            
            logger.info("✅ Manager user created successfully!");
        }
    }
}