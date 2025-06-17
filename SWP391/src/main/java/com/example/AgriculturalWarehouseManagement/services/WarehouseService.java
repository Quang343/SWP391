package com.example.AgriculturalWarehouseManagement.services;

import com.example.AgriculturalWarehouseManagement.models.Warehouse;
import com.example.AgriculturalWarehouseManagement.repositories.WarehouseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WarehouseService implements  IWarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Override
    public Warehouse findById(long id) {
        return warehouseRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Warehouse with this id does not exist"));
    }
}
