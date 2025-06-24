package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.ProductBatch;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockIn;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockInDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockInDetailRepository extends JpaRepository<StockInDetail, Integer> {
    List<StockInDetail> findByStockInID(StockIn stockInID);
    List<StockInDetail> findByBatchID(ProductBatch batchId);
}
