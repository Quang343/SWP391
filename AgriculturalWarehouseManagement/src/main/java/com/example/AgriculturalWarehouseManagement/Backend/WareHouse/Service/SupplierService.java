package com.example.AgriculturalWarehouseManagement.Backend.WareHouse.Service;

import com.example.AgriculturalWarehouseManagement.Backend.WareHouse.DTO.Request.SupplierDTO;
import com.example.AgriculturalWarehouseManagement.Backend.WareHouse.Entities.Suppliers;
import com.example.AgriculturalWarehouseManagement.Backend.WareHouse.Mapper.SupplierMapper;
import com.example.AgriculturalWarehouseManagement.Backend.WareHouse.Repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierMapper supplierMapper;

    // Get All Suppliers
    public List<Suppliers> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    // Get Suppliers by ID
    public SupplierDTO getSupplierById(Integer supplierID) {
        Optional<Suppliers> supplier = supplierRepository.findById(supplierID);
        return supplier.map(supplierMapper::supplierToSupplierDTO).orElse(null);
    }

    // Create Suppliers
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
        Suppliers supplier = supplierMapper.supplierDTOToSupplier(supplierDTO);
        supplier = supplierRepository.save(supplier);
        return supplierMapper.supplierToSupplierDTO(supplier);
    }

    // Update Suppliers
    public SupplierDTO updateSupplier(Integer supplierID, SupplierDTO supplierDTO) {
        if (!supplierRepository.existsById(supplierID)) {
            return null;
        }
        Suppliers supplier = supplierMapper.supplierDTOToSupplier(supplierDTO);
        supplier.setSupplierID(supplierID);
        supplier = supplierRepository.save(supplier);
        return supplierMapper.supplierToSupplierDTO(supplier);
    }

    // Delete Suppliers
    public void deleteSupplier(Integer supplierID) {
        supplierRepository.deleteById(supplierID);
    }
    }