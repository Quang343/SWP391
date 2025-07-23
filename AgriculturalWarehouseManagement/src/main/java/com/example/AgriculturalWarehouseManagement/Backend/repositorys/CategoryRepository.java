package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByNameIgnoreCase(String name);
    List<Category> findCategoryByStatusIn(List<String> status);

    @Query(value = """
            SELECT c.CategoryID, c.CategoryName, c.Description, c.status, c.imageurl, count(p.ProductID) AS totalProducts
            FROM category c
            JOIN product p on c.CategoryID = p.CategoryID
            WHERE c.status = "Active"
            GROUP BY c.CategoryID, c.CategoryName, c.Description, c.status, c.imageurl
            """, nativeQuery = true)
    List<Object[]> rawAllCategoriesAndCountProducts();
}
