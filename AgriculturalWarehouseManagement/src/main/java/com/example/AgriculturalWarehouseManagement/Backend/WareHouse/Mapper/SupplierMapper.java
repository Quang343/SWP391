package com.example.AgriculturalWarehouseManagement.Backend.WareHouse.Mapper;


import com.example.AgriculturalWarehouseManagement.Backend.WareHouse.DTO.Request.SupplierDTO;
import com.example.AgriculturalWarehouseManagement.Backend.WareHouse.Entities.Suppliers;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);

    SupplierDTO supplierToSupplierDTO(Suppliers supplier);

    Suppliers supplierDTOToSupplier(SupplierDTO supplierDTO);
}
