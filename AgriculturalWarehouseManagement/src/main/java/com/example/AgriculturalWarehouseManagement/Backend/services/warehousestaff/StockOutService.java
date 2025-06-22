package com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockOutDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.StockOutMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.Order;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockOut;
import com.example.AgriculturalWarehouseManagement.Backend.models.Warehouse;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.OrderRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.StockOutRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    public StockOutDTO createStockOut(StockOutDTO stockOutDTO) {
        StockOut stockOut = stockOutMapper.toEntity(stockOutDTO);
        if (stockOutDTO.getWarehouseID() != null) {
            Warehouse warehouse = warehouseRepository.findById(stockOutDTO.getWarehouseID())
                    .orElseThrow(() -> new RuntimeException("Warehouse not found"));
            stockOut.setWarehouseID(warehouse);
        }
        if (stockOutDTO.getOrderID() != null) {
            Order order = orderRepository.findById(Long.valueOf(stockOutDTO.getOrderID()))
                    .orElseThrow(() -> new RuntimeException("Order not found"));
            stockOut.setOrderID(order);
        }
        StockOut savedStockOut = stockOutRepository.save(stockOut);
        return stockOutMapper.toDTO(savedStockOut);
    }

    public StockOutDTO updateStockOut(Integer id, StockOutDTO stockOutDTO) {
        StockOut stockOut = stockOutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StockOut not found"));
        stockOut.setStockOutDate(stockOutDTO.getStockOutDate());
        stockOut.setNote(stockOutDTO.getNote());
        if (stockOutDTO.getWarehouseID() != null) {
            Warehouse warehouse = warehouseRepository.findById(stockOutDTO.getWarehouseID())
                    .orElseThrow(() -> new RuntimeException("Warehouse not found"));
            stockOut.setWarehouseID(warehouse);
        }
        if (stockOutDTO.getOrderID() != null) {
            Order order = orderRepository.findById(Long.valueOf(stockOutDTO.getOrderID()))
                    .orElseThrow(() -> new RuntimeException("Order not found"));
            stockOut.setOrderID(order);
        }
        StockOut updatedStockOut = stockOutRepository.save(stockOut);
        return stockOutMapper.toDTO(updatedStockOut);
    }

    public void deleteStockOut(Integer id) {
        stockOutRepository.deleteById(id);
    }
}
