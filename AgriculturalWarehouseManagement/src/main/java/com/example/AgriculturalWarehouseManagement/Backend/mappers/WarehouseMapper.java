package com.example.AgriculturalWarehouseManagement.Backend.mappers;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.WarehouseDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMapper {
    public WarehouseDTO toDTO(Warehouse warehouse) {
        return WarehouseDTO.builder()
                .warehouseId(warehouse.getId())
                .warehouseName(warehouse.getWarehouseName())
                .location(warehouse.getLocation())
                .description(warehouse.getDescription())
                .build();
    }
}
