package com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.WarehouseDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.WarehouseMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.Warehouse;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    public List<WarehouseDTO> getAllWarehouses() {
        return warehouseRepository.findAll()
                .stream()
                .map(warehouseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public WarehouseDTO getWarehouseById(Integer id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id: " + id));
        return warehouseMapper.toDTO(warehouse);
    }
}
