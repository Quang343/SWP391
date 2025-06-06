package com.example.AgriculturalWarehouseManagement.Backend.mappers;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.SupplierDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Suppliers;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    public SupplierDTO supplierToSupplierDTO(Suppliers supplier) {
        SupplierDTO dto = new SupplierDTO();
        dto.setSupplierID(supplier.getSupplierID());
        dto.setSupplierName(supplier.getSupplierName());
        dto.setContactInfo(supplier.getContactInfo());
        return dto;
    }

    public  Suppliers supplierDTOToSupplier(SupplierDTO dto) {
        Suppliers supplier = new Suppliers();
        supplier.setSupplierID(dto.getSupplierID());
        supplier.setSupplierName(dto.getSupplierName());
        supplier.setContactInfo(dto.getContactInfo());
        return supplier;
    }
}