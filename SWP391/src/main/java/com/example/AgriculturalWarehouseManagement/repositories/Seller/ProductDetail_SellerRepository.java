package com.example.AgriculturalWarehouseManagement.repositories.Seller;

import com.example.AgriculturalWarehouseManagement.models.Category;
import com.example.AgriculturalWarehouseManagement.models.CategoryWeight;
import com.example.AgriculturalWarehouseManagement.models.Product;
import com.example.AgriculturalWarehouseManagement.models.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDetail_SellerRepository extends JpaRepository<ProductDetail, Long> {

    List<ProductDetail> findByProductID_Id(Long productId);

    boolean existsByProductIDAndCategoryWeightID(Product product, CategoryWeight weight);
}

