package com.example.AgriculturalWarehouseManagement.Backend.services.admin;



import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.CategoryDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    List<Category> findCategoryByStatusIn(List<String> status);
    Category findById(Long id) throws Exception;
    Category createCategory(CategoryDTO categoryDTO) ;
    Category updateCategory(Long id, CategoryDTO categoryDTO) throws Exception;
    void  deleteCategory(Long id);
    boolean existsByNameIgnoreCase(String name);

    Page<Category> findAll(Pageable pageable);
}
