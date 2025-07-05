package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.StockOutDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockOutDetailRepository extends JpaRepository<StockOutDetail, Integer> {
    List<StockOutDetail> findByStockOutID_Id(Integer stockOutID);
}
