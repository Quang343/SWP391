package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Suppliers, Integer> {
    boolean existsBySupplierName(String supplierName);

    boolean existsBySupplierNameAndSupplierIDNot(String supplierName, Integer supplierID);

    boolean existsByContactInfo(String contactInfo);

    boolean existsByContactInfoAndSupplierIDNot(String contactInfo, Integer supplierID);
}