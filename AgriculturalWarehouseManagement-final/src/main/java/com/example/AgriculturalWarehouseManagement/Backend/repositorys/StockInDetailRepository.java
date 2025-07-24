package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.ProductBatch;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockIn;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockInDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StockInDetailRepository extends JpaRepository<StockInDetail, Integer> {
    List<StockInDetail> findByStockInID(StockIn stockInID);
    Optional<StockInDetail> findByBatchID(ProductBatch batch);

    @Query("SELECT CONCAT(YEAR(s.stockInDate), '-', LPAD(CAST(MONTH(s.stockInDate) AS string), 2, '0')) AS month, " +
            "SUM(CAST(d.unitPrice AS long) * CAST(d.quantity AS long)) AS totalSpent " +
            "FROM StockInDetail d JOIN d.stockInID s " +
            "WHERE d.unitPrice IS NOT NULL AND d.quantity IS NOT NULL " +
            "GROUP BY YEAR(s.stockInDate), MONTH(s.stockInDate) " +
            "ORDER BY YEAR(s.stockInDate), MONTH(s.stockInDate)")
    List<Object[]> findTotalSpentByMonth();
}
