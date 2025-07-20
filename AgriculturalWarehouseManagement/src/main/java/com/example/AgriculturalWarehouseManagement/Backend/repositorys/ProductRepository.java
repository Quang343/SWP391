package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductStatus;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.ProductStockProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    boolean existsByNameIgnoreCase(String name);

    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);
    Page<Product> findByStatus(ProductStatus status, Pageable pageable);
    Page<Product> findByCategoryIdAndStatus(Long categoryId, ProductStatus status, Pageable pageable);

    //thanh
    @Query("""
    SELECT 
        p.id AS productId,
        p.name AS productName,
        SUM(pb.importedQuantity - pb.soldQuantity) AS totalRemaining
    FROM Product p
    JOIN p.productDetails pd
    JOIN pd.productBatches pb
    GROUP BY p.id, p.name
    """)
    List<ProductStockProjection> getRemainingStockByProduct();

    @Query("""
    SELECT SUM(pb.importedQuantity - pb.soldQuantity)
    FROM ProductBatch pb
    """)
    Integer getTotalRemainingAllProducts();
}
