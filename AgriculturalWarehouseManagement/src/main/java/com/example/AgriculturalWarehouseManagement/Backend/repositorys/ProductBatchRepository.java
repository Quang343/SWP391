package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.ProductBatchDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductBatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductBatchRepository extends CrudRepository<ProductBatch, Integer> {
    @Query("SELECT new com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.ProductBatchDTO(" +
            "b.batchId, b.productDetail.productDetailId, b.manufactureDate, b.importedQuantity, b.soldQuantity) " +
            "FROM ProductBatch b " +
            "WHERE b.batchId NOT IN (SELECT sid.batchID.batchId FROM StockInDetail sid WHERE sid.batchID IS NOT NULL)")
    List<ProductBatchDTO> findAvailableBatches();

    @Query("SELECT p FROM ProductBatch p ORDER BY p.batchId ASC")
    Page<ProductBatch> findPaginatedProductBatches(Pageable pageable);

    @Query("SELECT b FROM ProductBatch b WHERE b.productDetail.productDetailId = :productDetailId ORDER BY b.manufactureDate ASC")
    List<ProductBatch> findByProductDetailIdOrderByManufactureDateAsc(@Param("productDetailId") Long productDetailId);
}
