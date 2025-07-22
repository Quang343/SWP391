package com.example.AgriculturalWarehouseManagement.Backend.mappers;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockInDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockIn;
import com.example.AgriculturalWarehouseManagement.Backend.models.Suppliers;
import com.example.AgriculturalWarehouseManagement.Backend.models.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class StockInMapper
{
    public static StockInDTO toDTO(StockIn stockin) {
        if (stockin == null) {
            return null;
        }
        StockInDTO dto = new StockInDTO();
        dto.setId(stockin.getId());
        if (stockin.getSupplierID() != null) {
            dto.setSupplierID(stockin.getSupplierID().getSupplierID());
        }
        if (stockin.getWarehouseID() != null) {
            dto.setWarehouseID(stockin.getWarehouseID().getId());
        }
        dto.setStockInDate(stockin.getStockInDate());
        dto.setNote(stockin.getNote());
        return dto;
    }

    public static StockIn toEntity(StockInDTO stockinDTO) {
        if (stockinDTO == null) {
            return null;
        }
        StockIn stockin = new StockIn();
        stockin.setId(stockinDTO.getId());
        stockin.setStockInDate(stockinDTO.getStockInDate());
        stockin.setNote(stockinDTO.getNote());

        // Gán Supplier entity từ ID
        if (stockinDTO.getSupplierID() != null) {
            Suppliers supplier = new Suppliers();
            supplier.setSupplierID(stockinDTO.getSupplierID());
            stockin.setSupplierID(supplier);
        }

        // Gán Warehouse entity từ ID
        if (stockinDTO.getWarehouseID() != null) {
            Warehouse warehouse = new Warehouse();
            warehouse.setId(stockinDTO.getWarehouseID());
            stockin.setWarehouseID(warehouse);
        }

        return stockin;
    }
}
