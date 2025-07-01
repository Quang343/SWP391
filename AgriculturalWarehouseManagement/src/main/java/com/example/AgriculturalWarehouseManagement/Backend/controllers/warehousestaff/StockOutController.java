package com.example.AgriculturalWarehouseManagement.Backend.controllers.warehousestaff;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.OrderDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockOutDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockOutDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.StockOutMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.Order;
import com.example.AgriculturalWarehouseManagement.Backend.models.OrderDetail;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockOut;
import com.example.AgriculturalWarehouseManagement.Backend.models.Warehouse;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.OrderRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.StockOutRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.WarehouseRepository;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.OrderDetailService;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.StockOutDetailService;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.StockOutService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stockouts")
public class StockOutController {
    @Autowired
    private StockOutService stockOutService;

    @Autowired
    private StockOutRepository stockOutRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private StockOutDetailService stockOutDetailService;

    @Autowired
    private StockOutMapper stockOutMapper;

    @PostMapping
    public ResponseEntity<StockOutDTO> createStockOut(@RequestBody StockOutDTO stockOutDTO) {
        if (stockOutDTO == null) {
            throw new RuntimeException("StockOutDTO cannot be null");
        }
        if (stockOutDTO.getWarehouseID() == null) {
            throw new RuntimeException("WarehouseID cannot be null");
        }
        if (stockOutDTO.getOrderID() == null) {
            throw new RuntimeException("OrderID cannot be null");
        }
        if (stockOutDTO.getStockOutDate() == null) {
            throw new RuntimeException("StockOutDate cannot be null");
        }
        StockOutDTO createdStockOut = stockOutService.createStockOut(stockOutDTO);
        return ResponseEntity.ok(createdStockOut);
    }

    @GetMapping
    public ResponseEntity<List<StockOutDTO>> getAllStockOuts() {
        return ResponseEntity.ok(stockOutService.getAllStockOuts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockOutDTO> getStockOutById(@PathVariable Integer id) {
        return ResponseEntity.ok(stockOutService.getStockOutById(id));
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