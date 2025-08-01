package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.StockOut;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockOutStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockOutRepository extends JpaRepository<StockOut, Integer> {
    @Query("SELECT s FROM StockOut s ORDER BY s.id ASC")
    Page<StockOut> findPaginatedStockOut(Pageable pageable);

    List<StockOut> findByOrderID_Id(Long orderId);

    List<StockOut> findByOrderID_IdIn(List<Long> canceledOrderIds);

    List<StockOut> findByStatus(StockOutStatus stockOutStatus);

    List<StockOut> findByOrderID_IdInAndStatus(List<Long> canceledOrderIds, StockOutStatus stockOutStatus);

    @Query("SELECT YEAR(s.stockOutDate), MONTH(s.stockOutDate), " +
            "SUM(d.quantity) " +
            "FROM StockOutDetail d JOIN d.stockOutID s " +
            "WHERE d.quantity IS NOT NULL AND s.status = 'EXPORTED' " +
            "GROUP BY YEAR(s.stockOutDate), MONTH(s.stockOutDate) " +
            "ORDER BY YEAR(s.stockOutDate), MONTH(s.stockOutDate)")
    List<Object[]> findTotalQuantityMonth();
}
