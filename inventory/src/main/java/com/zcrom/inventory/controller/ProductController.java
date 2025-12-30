package com.zcrom.inventory.controller;

import com.zcrom.inventory.entity.Product;
import com.zcrom.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = {"http://localhost:3000", "https://inventory-frontend-vert-eight.vercel.app"})
public class ProductController {

    @Autowired
    private ProductService productService;

    // ================= BASIC CRUD =================

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {
        try {
            Product updatedProduct = productService.updateProduct(id, product);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    // ================= INVENTORY =================

    @GetMapping("/low-stock")
    public List<Product> getLowStockProducts() {
        return productService.getLowStockProducts();
    }

    @GetMapping("/dashboard/stats")
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalProducts", productService.getTotalProductsCount());
        stats.put("totalValue", productService.getTotalInventoryValue());
        stats.put("lowStockCount", productService.getLowStockProducts().size());
        return stats;
    }

    // ================= REPORTS =================

    /**
     * Category-wise product count
     */
    @GetMapping("/report/category-distribution")
    public Map<String, Long> getCategoryDistribution() {
        List<Product> products = productService.getAllProducts();
        return products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.counting()
                ));
    }

    /**
     * Low stock detailed summary
     */
    @GetMapping("/report/low-stock-summary")
public List<Map<String, Object>> getLowStockSummary() {
    List<Product> lowStockProducts = productService.getLowStockProducts();

    return lowStockProducts.stream()
            .map(product -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", product.getId());
                map.put("productId", product.getProductId());
                map.put("name", product.getName());
                map.put("currentStock", product.getCurrentStock());
                map.put("minStockLevel", product.getMinStockLevel());
                map.put("difference",
                        product.getMinStockLevel() - product.getCurrentStock());
                map.put("category", product.getCategory());
                return map;
            })
            .collect(Collectors.toList());
}

    /**
     * Inventory value grouped by category
     */
    @GetMapping("/report/value-by-category")
    public Map<String, Double> getValueByCategory() {
        List<Product> products = productService.getAllProducts();
        return products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.summingDouble(
                                p -> p.getPrice() * p.getCurrentStock()
                        )
                ));
    }
}
