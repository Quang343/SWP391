package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.StockIn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface StockInRepository extends JpaRepository<StockIn,Integer> {
    @Query("SELECT s FROM StockIn s ORDER BY s.id ASC")
    Page<StockIn> findPaginatedStockIn(Pageable pageable);

}
