package com.example.AgriculturalWarehouseManagement.Backend.repositorys.seller;

import com.example.AgriculturalWarehouseManagement.Backend.models.ProductDetail;
import com.example.AgriculturalWarehouseManagement.Backend.models.SoldBySeller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SoldBySellerRepository extends JpaRepository<SoldBySeller, Long> {
    List<SoldBySeller> findByProductDetail(ProductDetail detail);
}

