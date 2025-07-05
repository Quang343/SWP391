package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.Suppliers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SupplierRepository extends JpaRepository<Suppliers, Integer> {
    @Query("SELECT s FROM Suppliers s ORDER BY s.supplierID ASC")
    Page<Suppliers> findPaginatedSuppliers(Pageable pageable);

    boolean existsBySupplierName(String supplierName);

    boolean existsBySupplierNameAndSupplierIDNot(String supplierName, Integer supplierID);

    boolean existsByContactInfo(String contactInfo);

    boolean existsByContactInfoAndSupplierIDNot(String contactInfo, Integer supplierID);
}