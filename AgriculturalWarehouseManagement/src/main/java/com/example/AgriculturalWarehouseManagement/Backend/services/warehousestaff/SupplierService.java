package com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.SupplierDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.SupplierMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.Suppliers;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    // Lấy danh sách nhà cung cấp với phân trang
    public Page<Suppliers> getPaginatedSuppliers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Suppliers> supplierPage = supplierRepository.findPaginatedSuppliers(pageable);
        // Debug output to console
        System.out.println("Debug - Page: " + page + ", Size: " + size + ", Total Pages: " + supplierPage.getTotalPages() + ", Total Elements: " + supplierPage.getTotalElements());
        return supplierPage;
    }

    // Get Suppliers by ID
    public SupplierDTO getSupplierById(Integer supplierID) {
        Optional<Suppliers> supplier = supplierRepository.findById(supplierID);
        return supplier.map(supplierMapper::supplierToSupplierDTO).orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    // Create Suppliers
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
        Suppliers supplier = supplierMapper.supplierDTOToSupplier(supplierDTO);
        supplier = supplierRepository.save(supplier);
        return supplierMapper.supplierToSupplierDTO(supplier);
    }

    public boolean existsBySupplierName(String supplierName) {
        return supplierRepository.existsBySupplierName(supplierName);
    }

    public SupplierDTO updateSupplier(Integer supplierID, SupplierDTO supplierDTO) {
        Optional<Suppliers> existingSupplier = supplierRepository.findById(supplierID);
        if (!existingSupplier.isPresent()) {
            return null;
        }

        Suppliers supplier = existingSupplier.get();
        supplier.setSupplierName(supplierDTO.getSupplierName());
        supplier.setContactInfo(supplierDTO.getContactInfo());
        // Chỉ cập nhật logo nếu supplierDTO có logo
        if (supplierDTO.getLogo() != null) {
            supplier.setLogo(supplierDTO.getLogo());
        }

        Suppliers updatedSupplier = supplierRepository.save(supplier);

        SupplierDTO result = new SupplierDTO();
        result.setSupplierID(updatedSupplier.getSupplierID());
        result.setSupplierName(updatedSupplier.getSupplierName());
        result.setContactInfo(updatedSupplier.getContactInfo());
        result.setLogo(updatedSupplier.getLogo());
        return result;
    }

    public boolean isSupplierNameExists(String name, Integer excludeSupplierId) {
        if (excludeSupplierId == null) {
            return supplierRepository.existsBySupplierName(name);
        }
        return supplierRepository.existsBySupplierNameAndSupplierIDNot(name, excludeSupplierId);
    }

    public boolean isContactInfoExists(String contactInfo, Integer excludeSupplierId) {
        if (excludeSupplierId == null) {
            return supplierRepository.existsByContactInfo(contactInfo.trim());
        }
        return supplierRepository.existsByContactInfoAndSupplierIDNot(contactInfo.trim(), excludeSupplierId);
    }




    // Delete Suppliers
    public void deleteSupplier(Integer supplierID) {
        supplierRepository.deleteById(supplierID);
    }
    }