package com.example.AgriculturalWarehouseManagement.Backend.controllers.warehousestaff;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.AdjustmentDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Adjustment;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.AdjustmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/adjustments")
@RequiredArgsConstructor
public class AdjustmentController  {
    private final AdjustmentService adjustmentService;

    @GetMapping
    public ResponseEntity<Iterable<Adjustment>> getAllAdjustments() {
        return ResponseEntity.ok(adjustmentService.getAllAdjustments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdjustmentDTO> getAdjustmentById(@PathVariable Integer id) {
        return ResponseEntity.ok(adjustmentService.getAdjustmentById(id));
    }

    @PostMapping
    public ResponseEntity<AdjustmentDTO> createAdjustment(@RequestBody AdjustmentDTO adjustmentDTO) {
        return ResponseEntity.ok(adjustmentService.createAdjustment(adjustmentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdjustmentDTO> updateAdjustment(@PathVariable Integer id, @RequestBody AdjustmentDTO adjustmentDTO) {
        return ResponseEntity.ok(adjustmentService.updateAdjustment(id, adjustmentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdjustment(@PathVariable Integer id) {
        adjustmentService.deleteAdjustment(id);
        return ResponseEntity.noContent().build();
    }
}
