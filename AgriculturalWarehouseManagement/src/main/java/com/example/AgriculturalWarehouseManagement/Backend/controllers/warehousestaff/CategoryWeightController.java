package com.example.AgriculturalWarehouseManagement.Backend.controllers.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.CategoryWeightDTO;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.CategoryWeightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoryweights")
@RequiredArgsConstructor
public class CategoryWeightController {

    private final CategoryWeightService service;

    @GetMapping
    public List<CategoryWeightDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryWeightDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryWeightDTO> create(@RequestBody CategoryWeightDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryWeightDTO> update(@PathVariable Integer id, @RequestBody CategoryWeightDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

