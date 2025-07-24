package com.example.AgriculturalWarehouseManagement.Backend.repositorys.seller;

import com.example.AgriculturalWarehouseManagement.Backend.models.CategoryWeight;
import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDetail_SellerRepository extends JpaRepository<ProductDetail, Long> {

    List<ProductDetail> findByProductID_Id(Long productId);

    boolean existsByProductIDAndCategoryWeightID(Product product, CategoryWeight weight);

    boolean existsByProductIDAndCategoryWeightIDAndProductDetailIdNot(Product product, CategoryWeight weight, Long excludeId);

}

