package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.StockOut;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockOutStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockOutRepository extends JpaRepository<StockOut, Integer> {
    List<StockOut> findByOrderID_Id(Long orderId);

    List<StockOut> findByOrderID_IdIn(List<Long> canceledOrderIds);

    List<StockOut> findByStatus(StockOutStatus stockOutStatus);

    List<StockOut> findByOrderID_IdInAndStatus(List<Long> canceledOrderIds, StockOutStatus stockOutStatus);
}
