package com.zcrom.inventory;

import com.zcrom.inventory.entity.Product;
import com.zcrom.inventory.entity.User;
import com.zcrom.inventory.entity.Supplier;
import com.zcrom.inventory.repository.ProductRepository;
import com.zcrom.inventory.repository.UserRepository;
import com.zcrom.inventory.repository.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (productRepository.count() == 0) {
            initializeProducts();
        }

        if (userRepository.count() == 0) {
            initializeUsers();
        }

        if (supplierRepository.count() == 0) {
            initializeSuppliers();
        }
    }

    private void initializeProducts() {
        List<Product> products = Arrays.asList(
            new Product(null, "ELEC1001", "Laptop", "Electronics", "piece", 899.99, 5, 15, null),
            new Product(null, "ELEC1002", "Smartphone", "Electronics", "piece", 699.99, 10, 8, null),
            new Product(null, "HARD1001", "Steel Bolts", "Hardware", "kg", 4.99, 100, 50, null),
            new Product(null, "RAW1001", "Plastic Pellets", "Raw Material", "kg", 2.99, 500, 200, null),
            new Product(null, "PROD1001", "Packaging Box", "Packaging", "piece", 1.49, 200, 75, null)
        );

        productRepository.saveAll(products);
        System.out.println("Sample products initialized");
    }

    private void initializeUsers() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole("ADMIN");

        User manager = new User();
        manager.setUsername("manager");
        manager.setPassword(passwordEncoder.encode("manager123"));
        manager.setRole("MANAGER");

        userRepository.save(admin);
        userRepository.save(manager);

        System.out.println("Sample users initialized");
    }

    private void initializeSuppliers() {
        List<Supplier> suppliers = Arrays.asList(
            new Supplier(null, "SUP1001", "ABC Electronics", "+1-555-0101",
                    "abc@example.com", "123 Tech Street, San Francisco", null),
            new Supplier(null, "SUP1002", "Global Hardware", "+1-555-0102",
                    "info@globalhardware.com", "456 Industry Ave, Chicago", null),
            new Supplier(null, "SUP1003", "Premium Raw Materials", "+1-555-0103",
                    "sales@premiumraw.com", "789 Factory Road, Detroit", null)
        );

        suppliers.forEach(Supplier::generateSupplierId);
        supplierRepository.saveAll(suppliers);

        System.out.println("Sample suppliers initialized");
    }
}