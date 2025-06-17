package com.example.AgriculturalWarehouseManagement.Backend.controllers.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.ProductBatchDTO;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.ProductBatchService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product-batches")
public class ProductBatchController {
    @Autowired
    private ProductBatchService service;

    @PostMapping
    public ResponseEntity<ProductBatchDTO> create(@Valid @RequestBody ProductBatchDTO dto) {
        try {
            return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{batchId}")
    public ResponseEntity<ProductBatchDTO> update(@PathVariable Integer batchId, @Valid @RequestBody ProductBatchDTO dto) {
        try {
            return ResponseEntity.ok(service.update(batchId, dto));
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{batchId}")
    public ResponseEntity<Void> delete(@PathVariable Integer batchId) {
        try {
            service.delete(batchId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductBatchDTO> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductBatchDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/adjustments")
    public ResponseEntity<Map<String, Object>> getAdjustmentsByBatchId(@RequestParam Integer batchId) {
        if (batchId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            return ResponseEntity.ok(service.getAdjustmentsByBatchId(batchId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}