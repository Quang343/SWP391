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

import com.example.AgriculturalWarehouseManagement.Backend.services.admin.order.OrderDetailService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.order.OrderService;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.StockOutDetailService;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.StockOutService;
import jakarta.servlet.http.HttpServletRequest;
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
    private StockOutService stockoutservice;

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

    @Autowired
    private OrderService orderService;

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

    @GetMapping("/returnstockout/details")
    public ResponseEntity<List<StockOutDTO>> getStockOutsForCanceledOrders() {
        // Lấy danh sách Order bị hủy
        List<Order> canceledOrders = orderService.findByStatus("CANCELLED");
        if (canceledOrders.isEmpty()) {
            return ResponseEntity.ok().body(List.of()); // Trả về danh sách rỗng nếu không có order bị hủy
        }

        // Lấy danh sách orderId từ các Order bị hủy
        List<Long> canceledOrderIds = canceledOrders.stream()
                .map(Order::getId)
                .collect(Collectors.toList());

        // Lọc các StockOut dựa trên orderId
        List<StockOutDTO> stockOutDTOs = canceledOrderIds.stream()
                .flatMap(orderId -> stockOutService.getStockOutsByOrderId(orderId).stream())
                .map(stockOutMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(stockOutDTOs);
    }

    @GetMapping("/returnstockout/details/processed")
    public ResponseEntity<List<StockOutDetailDTO>> getProcessedStockOutDetailsForCanceledOrders() {
        try {
            List<StockOutDetailDTO> processedDetails = stockOutService.processCanceledOrderReturns();
            return ResponseEntity.ok(processedDetails);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/exported-stockouts")
    public ResponseEntity<List<StockOutDTO>> getCanceledOrdersWithExportedStockOuts() {
        List<StockOutDTO> stockOutDTOs = stockoutservice.getCanceledOrdersWithExportedStockOuts();
        return ResponseEntity.ok(stockOutDTOs);
    }

    @PutMapping("/return/{id}/status")
    public ResponseEntity<StockOutDTO> updateStockOutStatusToReturned(@PathVariable Integer id) {
        StockOutDTO updatedStockOut = stockoutservice.updateStockOutStatusToReturned(id);
        return ResponseEntity.ok(updatedStockOut);
    }
}