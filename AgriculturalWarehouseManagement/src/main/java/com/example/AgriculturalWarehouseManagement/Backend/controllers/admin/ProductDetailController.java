package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.ProductDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-details")
public class ProductDetailController {
    @Autowired
    private ProductDetailService service;

    @PostMapping
    public ResponseEntity<ProductDetailDTO> create(@RequestBody ProductDetailDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDetailDTO> update(@PathVariable Integer id, @RequestBody ProductDetailDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductDetailDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}