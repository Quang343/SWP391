package com.example.AgriculturalWarehouseManagement.Backend.repositorys.seller;

import com.example.AgriculturalWarehouseManagement.Backend.models.Category;
import com.example.AgriculturalWarehouseManagement.Backend.models.CategoryWeight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryWeight_SellerRepository extends JpaRepository<CategoryWeight, Integer> {

    List<CategoryWeight> findByCategory_Id(Long categoryId);

    // ✅ Thêm đúng method này:
    Optional<CategoryWeight> findByCategoryAndWeightAndUnit(Category category, Double weight, String unit);
}




