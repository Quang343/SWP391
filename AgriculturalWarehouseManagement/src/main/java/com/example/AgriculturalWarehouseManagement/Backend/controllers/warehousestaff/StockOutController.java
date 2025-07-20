package com.example.AgriculturalWarehouseManagement.Backend.controllers.warehousestaff;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockOutDTO;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.StockOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stockouts")
public class StockOutController {
    @Autowired
    private StockOutService stockOutService;

    @GetMapping
    public ResponseEntity<List<StockOutDTO>> getAllStockOuts() {
        return ResponseEntity.ok(stockOutService.getAllStockOuts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockOutDTO> getStockOutById(@PathVariable Integer id) {
        return ResponseEntity.ok(stockOutService.getStockOutById(id));
    }

    @PostMapping
    public ResponseEntity<StockOutDTO> createStockOut(@RequestBody StockOutDTO stockOutDTO) {
        return ResponseEntity.ok(stockOutService.createStockOut(stockOutDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockOutDTO> updateStockOut(@PathVariable Integer id, @RequestBody StockOutDTO stockOutDTO) {
        return ResponseEntity.ok(stockOutService.updateStockOut(id, stockOutDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockOut(@PathVariable Integer id) {
        stockOutService.deleteStockOut(id);
        return ResponseEntity.noContent().build();
    }
}