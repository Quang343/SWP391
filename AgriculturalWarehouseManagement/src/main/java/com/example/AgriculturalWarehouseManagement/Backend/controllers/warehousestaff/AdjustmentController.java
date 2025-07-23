package com.example.AgriculturalWarehouseManagement.Backend.controllers.warehousestaff;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.AdjustmentDTO;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.AdjustmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adjustments")
@RequiredArgsConstructor
public class AdjustmentController  {
    private final AdjustmentService adjustmentService;

    // REST API for paginated adjustments
    @GetMapping("/paginatedAdjustment")
    @ResponseBody
    public Page<AdjustmentDTO> getPaginatedAdjustments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return adjustmentService.getPaginatedAdjustments(page, size);
    }

    // Thymeleaf view for paginated adjustments
    @GetMapping("/warehouse/adjustments")
    public String listAdjustments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        Page<AdjustmentDTO> adjustmentPage = adjustmentService.getPaginatedAdjustments(page, size);
        model.addAttribute("adjustments", adjustmentPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", adjustmentPage.getTotalPages());
        model.addAttribute("totalItems", adjustmentPage.getTotalElements());
        return "BackEnd/WareHouse/adjustment";
    }

    @GetMapping
    public ResponseEntity<List<AdjustmentDTO>> getAllAdjustments() {
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
