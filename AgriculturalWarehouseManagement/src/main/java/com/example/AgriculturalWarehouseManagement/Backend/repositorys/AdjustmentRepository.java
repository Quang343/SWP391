package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.Adjustment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AdjustmentRepository extends CrudRepository<Adjustment, Integer> {
    @Query("SELECT a FROM Adjustment a ORDER BY a.id ASC")
    Page<Adjustment> findPaginatedAdjustments(Pageable pageable);

    List<Adjustment> findByBatchBatchId(Integer batch_batchId);


}
