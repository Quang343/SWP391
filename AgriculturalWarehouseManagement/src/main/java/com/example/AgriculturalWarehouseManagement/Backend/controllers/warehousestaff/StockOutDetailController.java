package com.example.AgriculturalWarehouseManagement.Backend.controllers.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockOutDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.StockOutDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stockoutdetails")
public class StockOutDetailController {
    @Autowired
    private StockOutDetailService stockOutDetailService;

    @GetMapping
    public ResponseEntity<List<StockOutDetailDTO>> getAllStockOutDetails() {
        return ResponseEntity.ok(stockOutDetailService.getAllStockOutDetails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockOutDetailDTO> getStockOutDetailById(@PathVariable Integer id) {
        return ResponseEntity.ok(stockOutDetailService.getStockOutDetailById(id));
    }

    @PostMapping
    public ResponseEntity<StockOutDetailDTO> createStockOutDetail(@RequestBody StockOutDetailDTO stockOutDetailDTO) {
        return ResponseEntity.ok(stockOutDetailService.createStockOutDetail(stockOutDetailDTO));
    }

    @GetMapping("/stockout/{stockOutID}")
    public ResponseEntity<List<StockOutDetailDTO>> getStockOutDetailsByStockOutId(@PathVariable Integer stockOutID) {
        return ResponseEntity.ok(stockOutDetailService.getStockOutDetailsByStockOutId(stockOutID));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockOutDetailDTO> updateStockOutDetail(@PathVariable Integer id, @RequestBody StockOutDetailDTO stockOutDetailDTO) {
        return ResponseEntity.ok(stockOutDetailService.updateStockOutDetail(id, stockOutDetailDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockOutDetail(@PathVariable Integer id) {
        stockOutDetailService.deleteStockOutDetail(id);
        return ResponseEntity.noContent().build();
    }
}