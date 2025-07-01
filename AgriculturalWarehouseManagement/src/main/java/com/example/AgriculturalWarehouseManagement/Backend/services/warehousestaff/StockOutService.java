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
        if (stockOutDTO.getWarehouseID() == null) {
            throw new RuntimeException("WarehouseID cannot be null");
        }
        if (stockOutDTO.getOrderID() == null) {
            throw new RuntimeException("OrderID cannot be null");
        }
        StockOut stockOut = stockOutMapper.toEntity(stockOutDTO);
        Warehouse warehouse = warehouseRepository.findById(stockOutDTO.getWarehouseID())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        Order order = orderRepository.findById(Long.valueOf(stockOutDTO.getOrderID()))
                .orElseThrow(() -> new RuntimeException("Order not found"));
        stockOut.setWarehouseID(warehouse);
        stockOut.setOrderID(order);
        StockOut savedStockOut = stockOutRepository.save(stockOut);

        // Create and save StockOutDetails
        List<OrderDetail> orderDetails = orderDetailService.findOrderDetailsByOrderId(Long.valueOf(stockOutDTO.getOrderID()));
        if (orderDetails.isEmpty()) {
            throw new RuntimeException("No OrderDetails found for orderID: " + stockOutDTO.getOrderID());
        }
        List<StockOutDetailDTO> stockOutDetailDTOs = stockOutDetailService.createStockOutDetailsFromOrderDetails(savedStockOut.getId(), orderDetails);
        stockOutDetailService.saveStockOutDetails(stockOutDetailDTOs);

        return stockOutMapper.toDTO(savedStockOut);
    }



    public StockOutDTO updateStockOut(Integer id, StockOutDTO stockOutDTO) {
        StockOut stockOut = stockOutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StockOut not found"));
        stockOut.setStockOutDate(stockOutDTO.getStockOutDate());
        stockOut.setNote(stockOutDTO.getNote());
        if (stockOutDTO.getWarehouseID() == null) {
            throw new RuntimeException("WarehouseID cannot be null");
        }
        if (stockOutDTO.getOrderID() == null) {
            throw new RuntimeException("OrderID cannot be null");
        }
        Warehouse warehouse = warehouseRepository.findById(stockOutDTO.getWarehouseID())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        Order order = orderRepository.findById(Long.valueOf(stockOutDTO.getOrderID()))
                .orElseThrow(() -> new RuntimeException("Order not found"));
        stockOut.setWarehouseID(warehouse);
        stockOut.setOrderID(order);
        StockOut updatedStockOut = stockOutRepository.save(stockOut);
        return stockOutMapper.toDTO(updatedStockOut);
    }

    public void deleteStockOut(Integer id) {
        stockOutRepository.deleteById(id);
    }
}
