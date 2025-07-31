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

    @Query(value = """
    SELECT b.* FROM ProductBatch b
    JOIN productdetail pd ON b.ProductDetailID = pd.ProductDetailID
    WHERE b.ProductDetailID = :productDetailId
    AND DATE_SUB(DATE_ADD(b.ManufactureDate, INTERVAL pd.Expiry MONTH), INTERVAL 5 DAY) >= CURDATE()
    ORDER BY b.ManufactureDate ASC
""", nativeQuery = true)
    List<ProductBatch> findUnexpiredByProductDetailId(@Param("productDetailId") Long productDetailId);

    @Query(value = """
        SELECT 
            b.BatchID,
            b.ImportedQuantity,
            b.ManufactureDate,
            DATE_ADD(b.ManufactureDate, INTERVAL pd.Expiry MONTH) AS ExpiryDate
        FROM ProductBatch b
        JOIN ProductDetail pd ON b.ProductDetailID = pd.ProductDetailID
        WHERE DATE_SUB(DATE_ADD(b.ManufactureDate, INTERVAL pd.Expiry MONTH), INTERVAL 5 DAY) >= CURDATE()
        ORDER BY DATE_ADD(b.ManufactureDate, INTERVAL pd.Expiry MONTH) ASC
        LIMIT 4
    """, nativeQuery = true)
    List<Object[]> findTop4ExpiringSoon();
}
