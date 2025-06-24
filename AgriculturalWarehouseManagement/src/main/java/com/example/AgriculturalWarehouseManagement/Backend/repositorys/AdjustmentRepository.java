package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.Adjustment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AdjustmentRepository extends CrudRepository<Adjustment, Integer> {
    List<Adjustment> findByBatchBatchId(Integer batch_batchId);
}
