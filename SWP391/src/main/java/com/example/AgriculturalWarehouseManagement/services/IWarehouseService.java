package com.example.AgriculturalWarehouseManagement.services;

import com.example.AgriculturalWarehouseManagement.models.Warehouse;

public interface IWarehouseService {
    Warehouse findById(long id);
}
