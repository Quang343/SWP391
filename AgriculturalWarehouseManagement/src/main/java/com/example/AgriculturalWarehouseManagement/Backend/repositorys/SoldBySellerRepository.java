package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.SoldBySeller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoldBySellerRepository extends JpaRepository<SoldBySeller, Integer> {
}
