package com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.OrderDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockOutDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockOutDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.StockOutDetailMapper;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.StockOutMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.*;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.*;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.OrderDetailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockOutService {
    @Autowired
    private StockOutRepository stockOutRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockOutMapper stockOutMapper;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private StockOutDetailService stockOutDetailService;

    public List<StockOutDTO> getAllStockOuts() {
        return stockOutRepository.findAll().stream()
                .map(stockOutMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StockOutDTO getStockOutById(Integer id) {
        StockOut stockOut = stockOutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StockOut not found"));
        return stockOutMapper.toDTO(stockOut);
    }

    @Transactional
    public StockOutDTO createStockOut(StockOutDTO stockOutDTO) {
        System.out.println("Debug - Creating StockOut with DTO: " + stockOutDTO);
        if (stockOutDTO.getWarehouseID() == null) {
            throw new RuntimeException("WarehouseID cannot be null");
        }
        if (stockOutDTO.getOrderID() == null) {
            throw new RuntimeException("OrderID cannot be null");
        }
        StockOut stockOut = stockOutMapper.toEntity(stockOutDTO);
        Warehouse warehouse = warehouseRepository.findById(stockOutDTO.getWarehouseID())
                .orElseThrow(() -> new RuntimeException("Warehouse not found for ID: " + stockOutDTO.getWarehouseID()));
        Order order = orderRepository.findById(Long.valueOf(stockOutDTO.getOrderID()))
                .orElseThrow(() -> new RuntimeException("Order not found for ID: " + stockOutDTO.getOrderID()));
        stockOut.setWarehouseID(warehouse);
        stockOut.setOrderID(order);
        // Use provided stockOutDate if present, otherwise use current time
        stockOut.setStockOutDate(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));
        System.out.println("Debug - Saving StockOut: " + stockOut);
        StockOut savedStockOut = stockOutRepository.save(stockOut);

        // Create and save StockOutDetails
        List<OrderDetail> orderDetails = orderDetailService.findOrderDetailsByOrderId(Long.valueOf(stockOutDTO.getOrderID()));
        if (orderDetails.isEmpty()) {
            throw new RuntimeException("No OrderDetails found for orderID: " + stockOutDTO.getOrderID());
        }
        System.out.println("Debug - Found OrderDetails: " + orderDetails.size());
        List<StockOutDetailDTO> stockOutDetailDTOs = stockOutDetailService.createStockOutDetailsFromOrderDetails(savedStockOut.getId(), orderDetails);
        System.out.println("Debug - Created StockOutDetailDTOs: " + stockOutDetailDTOs.size());
        stockOutDetailService.saveStockOutDetails(stockOutDetailDTOs);

        // Update Order status to STOCK_OUT
        System.out.println("Debug - Updating Order ID: " + order.getId() + " status to STOCK_OUT");
        order.setStatus("STOCK_OUT");
        orderRepository.save(order);
        System.out.println("Debug - Order ID: " + order.getId() + " status updated to: " + order.getStatus());

        return stockOutMapper.toDTO(savedStockOut);
    }
}
