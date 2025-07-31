package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.ProductBatch;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockIn;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockInDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StockInDetailRepository extends JpaRepository<StockInDetail, Integer> {
    List<StockInDetail> findByStockInID(StockIn stockInID);
    Optional<StockInDetail> findByBatchID(ProductBatch batch);

    @Query("SELECT YEAR(s.stockInDate), MONTH(s.stockInDate), " +
            "SUM(d.unitPrice * d.quantity) " +
            "FROM StockInDetail d JOIN d.stockInID s " +
            "WHERE d.unitPrice IS NOT NULL AND d.quantity IS NOT NULL " +
            "GROUP BY YEAR(s.stockInDate), MONTH(s.stockInDate) " +
            "ORDER BY YEAR(s.stockInDate), MONTH(s.stockInDate)")
    List<Object[]> findTotalSpentByMonth();

    @Query("SELECT YEAR(s.stockInDate), MONTH(s.stockInDate), " +
            "SUM(d.quantity) " +
            "FROM StockInDetail d JOIN d.stockInID s " +
            "WHERE d.unitPrice IS NOT NULL AND d.quantity IS NOT NULL " +
            "GROUP BY YEAR(s.stockInDate), MONTH(s.stockInDate) " +
            "ORDER BY YEAR(s.stockInDate), MONTH(s.stockInDate)")
    List<Object[]> findTotalQuantityMonth();


    @Query("SELECT s.supplierID, SUM(d.quantity) AS total " +
            "FROM StockInDetail d JOIN d.stockInID s " +
            "WHERE d.quantity IS NOT NULL " +
            "GROUP BY s.supplierID " +
            "ORDER BY total DESC")
    List<Object[]> findTopSuppliers(Pageable pageable);
}
