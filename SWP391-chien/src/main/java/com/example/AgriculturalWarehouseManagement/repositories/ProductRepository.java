package com.example.AgriculturalWarehouseManagement.repositories;

import com.example.AgriculturalWarehouseManagement.models.Product;
import com.example.AgriculturalWarehouseManagement.models.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    boolean existsByNameIgnoreCase(String name);

}
