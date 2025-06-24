package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.Suppliers;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface SupplierRepository extends JpaRepository<Suppliers, Integer> {
   // Page<Suppliers> findAll(Pageable pageable);

    boolean existsBySupplierName(String supplierName);

    boolean existsBySupplierNameAndSupplierIDNot(String supplierName, Integer supplierID);

    boolean existsByContactInfo(String contactInfo);

    boolean existsByContactInfoAndSupplierIDNot(String contactInfo, Integer supplierID);
}