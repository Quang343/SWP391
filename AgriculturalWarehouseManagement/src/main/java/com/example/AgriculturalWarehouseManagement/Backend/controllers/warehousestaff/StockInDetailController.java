package com.example.AgriculturalWarehouseManagement.Backend.controllers.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockInDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockInDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.StockInMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockIn;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockInDetail;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.StockInRepository;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.StockInDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public ResponseEntity<Integer> getQuantityByBatchId(@PathVariable("batchId") Integer batchId) {
        try {
            StockInDetailDTO stockInDetail = stockInDetailService.findByBatchId(batchId);
            return ResponseEntity.ok(stockInDetail.getQuantity());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/importprice/{batchId}")
    public ResponseEntity<Integer> getImportPriceByBatchId(@PathVariable("batchId") Integer batchId) {
        try {
            Integer importPrice = stockInDetailService.getImportPriceByBatchId(batchId);
            return ResponseEntity.ok(importPrice);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/total-spent")
    public ResponseEntity<BigDecimal> getTotalSpent() {
        BigDecimal total = stockInDetailService.calculateTotalSpent();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/{stockInId}/batch-count")
    public ResponseEntity<Integer> countBatches(@PathVariable Long stockInId) {
        try {
            int count = stockInDetailService.countBatchesInStockIn(stockInId);
            return ResponseEntity.ok(count);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
        }
    }
}
