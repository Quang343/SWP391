package com.example.AgriculturalWarehouseManagement.Backend.controllers.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockInDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockInDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.StockInMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockIn;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.StockInRepository;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.StockInDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stockindetails")
public class StockInDetailController {
    @Autowired
    private StockInDetailService stockInDetailService;

    @PostMapping
    public ResponseEntity<StockInDetailDTO> createStockInDetail(@RequestBody StockInDetailDTO stockInDetailDTO) {
        StockInDetailDTO createdStockInDetail = stockInDetailService.createStockInDetail(stockInDetailDTO);
        return ResponseEntity.ok(createdStockInDetail);
    }

    @GetMapping
    public ResponseEntity<List<StockInDetailDTO>> getAllStockInDetails() {
        List<StockInDetailDTO> stockInDetails = stockInDetailService.getAllStockInDetails();
        return ResponseEntity.ok(stockInDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockInDetailDTO> getStockInDetailById(@PathVariable Integer id) {
        StockInDetailDTO stockInDetailDTO = stockInDetailService.getStockInDetailById(id);
        return ResponseEntity.ok(stockInDetailDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockInDetail(@PathVariable Integer id) {
        stockInDetailService.deleteStockInDetail(id);
        return ResponseEntity.noContent().build();
    }
}
