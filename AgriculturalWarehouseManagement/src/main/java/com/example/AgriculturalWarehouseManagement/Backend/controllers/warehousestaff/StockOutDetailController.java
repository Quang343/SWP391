package com.example.AgriculturalWarehouseManagement.Backend.controllers.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.OrderDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockOutDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.OrderDetail;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockOut;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.StockOutRepository;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.OrderDetailService;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.StockOutDetailService;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.StockOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stockoutdetails")
public class StockOutDetailController {
    @Autowired
    private StockOutDetailService stockOutDetailService;

    @Autowired
    private StockOutService stockOutService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private StockOutRepository stockOutRepository;

    @GetMapping("/{stockOutId}/prepare")
    public ResponseEntity<List<StockOutDetailDTO>> prepareStockOutDetails(@PathVariable Integer stockOutId) {
        if (stockOutId == null) {
            throw new RuntimeException("StockOutID cannot be null");
        }

        // Fetch StockOut to get orderId
        StockOut stockOut = stockOutRepository.findById(stockOutId)
                .orElseThrow(() -> new RuntimeException("StockOut not found for ID: " + stockOutId));
        Long orderId = stockOut.getOrderID() != null ? stockOut.getOrderID().getId() : null;
        if (orderId == null) {
            throw new RuntimeException("OrderID cannot be null for StockOut ID: " + stockOutId);
        }

        // Fetch OrderDetail entities
        List<OrderDetail> orderDetails = orderDetailService.findOrderDetailsByOrderId(orderId);
        if (orderDetails.isEmpty()) {
            throw new RuntimeException("No OrderDetails found for orderID: " + orderId);
        }

        // Create StockOutDetailDTOs
        List<StockOutDetailDTO> stockOutDetailDTOs = stockOutDetailService.createStockOutDetailsFromOrderDetails(stockOutId, orderDetails);
        return ResponseEntity.ok(stockOutDetailDTOs);
    }

    @PostMapping("/{stockOutId}/details")
    public ResponseEntity<List<StockOutDetailDTO>> createStockOutDetails(@PathVariable Integer stockOutId) {
        if (stockOutId == null) {
            throw new RuntimeException("StockOutID cannot be null");
        }

        // Fetch StockOut to get orderId
        StockOut stockOut = stockOutRepository.findById(stockOutId)
                .orElseThrow(() -> new RuntimeException("StockOut not found for ID: " + stockOutId));
        Long orderId = stockOut.getOrderID() != null ? stockOut.getOrderID().getId() : null;
        if (orderId == null) {
            throw new RuntimeException("OrderID cannot be null for StockOut ID: " + stockOutId);
        }

        // Fetch OrderDetail entities
        List<OrderDetail> orderDetails = orderDetailService.findOrderDetailsByOrderId(orderId);
        if (orderDetails.isEmpty()) {
            throw new RuntimeException("No OrderDetails found for orderID: " + orderId);
        }

        // Create and save StockOutDetails
        List<StockOutDetailDTO> stockOutDetailDTOs = stockOutDetailService.createStockOutDetailsFromOrderDetails(stockOutId, orderDetails);
        stockOutDetailService.saveStockOutDetails(stockOutDetailDTOs);
        return ResponseEntity.ok(stockOutDetailDTOs);
    }

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