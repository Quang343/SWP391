package com.example.AgriculturalWarehouseManagement.Backend.controllers.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.ProductBatchDTO;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductBatchRepository;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.ProductBatchService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product-batches")
public class ProductBatchController {
    @Autowired
    private ProductBatchService service;

    @Autowired
    private ProductBatchRepository productBatchRepository;

    @Autowired
    private ProductBatchService productBatchService;

    // API endpoint for paginated product batches
    @GetMapping("/paginatedProductBatches")
    @ResponseBody
    public Page<ProductBatchDTO> getPaginatedProductBatches(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size) {
        return productBatchService.getPaginatedProductBatches(page, size);
    }

    @GetMapping("/productdetail/{productDetailId}")
    public ResponseEntity<List<ProductBatchDTO>> getProductBatchesByProductDetailId(@PathVariable Long productDetailId) {
        if (productDetailId == null) {
            throw new RuntimeException("ProductDetailId cannot be null");
        }
        List<ProductBatchDTO> productBatches = productBatchService.getProductBatchesByProductDetailId(productDetailId);
        return ResponseEntity.ok(productBatches);
    }

    // Thymeleaf view for product batch list with pagination
    @GetMapping("/warehouse/productBatches")
    public String listProductBatches(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            Model model) {
        Page<ProductBatchDTO> productBatchPage = productBatchService.getPaginatedProductBatches(page, size);
        model.addAttribute("productBatches", productBatchPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productBatchPage.getTotalPages());
        model.addAttribute("totalItems", productBatchPage.getTotalElements());
        return "BackEnd/WareHouse/productbatch";
    }

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

    @GetMapping("/available")
    public List<ProductBatchDTO> getAvailableBatches() {
        List<ProductBatchDTO> availableBatches = productBatchRepository.findAvailableBatches();
        System.out.println("Available Batches: " + availableBatches);
        return availableBatches;
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countAllProductBatches() {
        long count = productBatchService.countAll();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/top4-remaining")
    public ResponseEntity<List<ProductBatchDTO>> getTop4RemainingBatches() {
        return ResponseEntity.ok(productBatchService.getTop4RemainingBatches());
    }

    @GetMapping("/total-quantity-by-product-detail")
    public ResponseEntity<Map<Integer, Integer>> getTotalQuantityByProductDetail() {
        try {
            Map<Integer, Integer> totalQuantityMap = productBatchService.getTotalQuantityByProductDetail();
            return ResponseEntity.ok(totalQuantityMap);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Handle exception appropriately in production
        }
    }

    @GetMapping("/expiring-soon")
    public ResponseEntity<List<Map<String, Object>>> getTop4ExpiringSoonBatches() {
        List<Map<String, Object>> batches = productBatchService.getTop4ExpiringSoonBatches();
        return ResponseEntity.ok(batches);
    }
}