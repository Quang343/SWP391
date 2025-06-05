package com.example.AgriculturalWarehouseManagement.Backend.WareHouse.Repository;


import com.example.AgriculturalWarehouseManagement.Backend.WareHouse.Entities.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Suppliers, Integer> {
}