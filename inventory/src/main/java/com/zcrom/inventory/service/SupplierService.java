// src/main/java/com/zcrom/inventory/service/SupplierService.java
package com.zcrom.inventory.service;

import com.zcrom.inventory.entity.Supplier;
import com.zcrom.inventory.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    
    @Autowired
    private SupplierRepository supplierRepository;
    
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
    
    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }
    
    public Supplier createSupplier(Supplier supplier) {
        supplier.generateSupplierId();
        return supplierRepository.save(supplier);
    }
    
    public Supplier updateSupplier(Long id, Supplier supplierDetails) {
        return supplierRepository.findById(id)
            .map(supplier -> {
                supplier.setName(supplierDetails.getName());
                supplier.setPhone(supplierDetails.getPhone());
                supplier.setEmail(supplierDetails.getEmail());
                supplier.setAddress(supplierDetails.getAddress());
                return supplierRepository.save(supplier);
            })
            .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
    }
    
    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
    
    public long getTotalSuppliersCount() {
        return supplierRepository.count();
    }
}