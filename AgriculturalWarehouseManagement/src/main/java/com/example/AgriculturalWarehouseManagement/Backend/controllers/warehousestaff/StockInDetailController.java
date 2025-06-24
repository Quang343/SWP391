package com.example.AgriculturalWarehouseManagement.Backend.controllers.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockInDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockInDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.StockInMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockIn;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.StockInRepository;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.StockInDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PutMapping("/{id}")
    public ResponseEntity<StockInDetailDTO> updateStockInDetail(@PathVariable Integer id, @RequestBody StockInDetailDTO stockInDetailDTO) {
        try {
            StockInDetailDTO updatedDTO = stockInDetailService.updateStockInDetail(id, stockInDetailDTO);
            return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/quantity/{batchId}")
    public ResponseEntity<List<StockInDetailDTO>> getStockInDetailsByBatchId(@PathVariable("batchId") Integer batchId) {
        try {
            List<StockInDetailDTO> stockInDetails = stockInDetailService.findByBatchId(batchId);
            return ResponseEntity.ok(stockInDetails);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
