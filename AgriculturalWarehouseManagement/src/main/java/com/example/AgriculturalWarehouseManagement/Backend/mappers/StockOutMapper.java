package com.example.AgriculturalWarehouseManagement.Backend.mappers;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockOutDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockOut;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockOutStatus;
import org.springframework.stereotype.Component;

@Component
public class StockOutMapper {
    public StockOutDTO toDTO(StockOut stockOut) {
        if (stockOut == null) {
            return null;
        }
        StockOutDTO stockOutDTO = new StockOutDTO();
        stockOutDTO.setId(stockOut.getId());
        stockOutDTO.setWarehouseID(stockOut.getWarehouseID() != null ? stockOut.getWarehouseID().getId() : null);
        stockOutDTO.setOrderID(Math.toIntExact(stockOut.getOrderID() != null ? stockOut.getOrderID().getId() : null));
        stockOutDTO.setStockOutDate(stockOut.getStockOutDate());
        stockOutDTO.setNote(stockOut.getNote());
        stockOutDTO.setStatus(stockOut.getStatus());
        return stockOutDTO;
    }

    public StockOut toEntity(StockOutDTO stockOutDTO) {
        if (stockOutDTO == null) {
            return null;
        }
        StockOut stockOut = new StockOut();
        stockOut.setId(stockOutDTO.getId());
        // Note: Warehouse and Order entities are not set here; they are handled in the service layer
        stockOut.setStockOutDate(stockOutDTO.getStockOutDate());
        stockOut.setStatus(stockOutDTO.getStatus());
        return stockOut;
    }
}
