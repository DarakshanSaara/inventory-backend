package com.zcrom.inventory.service;

import com.zcrom.inventory.entity.Product;
import com.zcrom.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    
    public Product createProduct(Product product) {
        product.generateProductId();
        return productRepository.save(product);
    }
    
    public Product updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id)
            .map(product -> {
                product.setName(productDetails.getName());
                product.setCategory(productDetails.getCategory());
                product.setUnit(productDetails.getUnit());
                product.setPrice(productDetails.getPrice());
                product.setMinStockLevel(productDetails.getMinStockLevel());
                product.setCurrentStock(productDetails.getCurrentStock());
                return productRepository.save(product);
            })
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }
    
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
    public List<Product> getLowStockProducts() {
        // Get all products and filter for low stock
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream()
            .filter(p -> p.getCurrentStock() < p.getMinStockLevel())
            .toList();
    }
    
    public long getTotalProductsCount() {
        return productRepository.count();
    }
    
    public double getTotalInventoryValue() {
        List<Product> products = productRepository.findAll();
        return products.stream()
            .mapToDouble(p -> p.getPrice() * p.getCurrentStock())
            .sum();
    }
}