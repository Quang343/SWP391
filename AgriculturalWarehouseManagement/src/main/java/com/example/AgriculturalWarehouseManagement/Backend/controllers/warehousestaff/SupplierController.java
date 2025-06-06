package com.example.AgriculturalWarehouseManagement.Backend.controllers.warehousestaff;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.SupplierDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Suppliers;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // Get All Suppliers
    @GetMapping
    public List<Suppliers> getAllSuppliers() {
        return supplierService.getAllSuppliers(); // Trả về danh sách tất cả các Suppliers
    }

    // Get Suppliers by ID

    @GetMapping("/{supplierID}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable Integer supplierID) {
        SupplierDTO dto = supplierService.getSupplierById(supplierID);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto); // JSON
    }

    // Create Suppliers
    @PostMapping
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody SupplierDTO supplierDTO) {
        SupplierDTO createdSupplier = supplierService.createSupplier(supplierDTO);
        return new ResponseEntity<>(createdSupplier, HttpStatus.CREATED);
    }

    // Update Suppliers
    @PutMapping("/{supplierID}")
    public ResponseEntity<SupplierDTO> updateSupplier(@PathVariable Integer supplierID, @RequestBody SupplierDTO supplierDTO) {
        SupplierDTO updatedSupplier = supplierService.updateSupplier(supplierID, supplierDTO);
        return updatedSupplier != null ? ResponseEntity.ok(updatedSupplier) : ResponseEntity.notFound().build();
    }

    // Delete Suppliers
    @DeleteMapping("/{supplierID}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Integer supplierID) {
        supplierService.deleteSupplier(supplierID);
        return ResponseEntity.noContent().build();
    }
}