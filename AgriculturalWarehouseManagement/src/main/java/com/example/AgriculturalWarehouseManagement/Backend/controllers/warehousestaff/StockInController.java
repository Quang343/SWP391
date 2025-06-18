package com.example.AgriculturalWarehouseManagement.Backend.controllers.warehousestaff;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockInDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockInDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.StockInDetailService;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.StockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stockins")
public class StockInController {
    @Autowired
    private StockInService stockInService;

    @Autowired
    private StockInDetailService stockInDetailService; // Thêm để lấy chi tiết

    @PostMapping
    public ResponseEntity<StockInDTO> createStockIn(@RequestBody StockInDTO stockInDTO) {
        StockInDTO createdStockIn = stockInService.createStockIn(stockInDTO);
        return ResponseEntity.ok(createdStockIn);
    }

    @GetMapping
    public ResponseEntity<List<StockInDTO>> getAllStockIns() {
        List<StockInDTO> stockIns = stockInService.getAllStockIns();
        return ResponseEntity.ok(stockIns);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockInDTO> getStockInById(@PathVariable Integer id) {
        StockInDTO stockInDTO = stockInService.getStockInById(id);
        return ResponseEntity.ok(stockInDTO);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<List<StockInDetailDTO>> getStockInDetailsByStockInId(@PathVariable Integer id) {
        List<StockInDetailDTO> stockInDetails = stockInDetailService.getStockInDetailsByStockInId(id);
        return ResponseEntity.ok(stockInDetails);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockIn(@PathVariable Integer id) {
        stockInService.deleteStockIn(id);
        return ResponseEntity.noContent().build();
    }
}
