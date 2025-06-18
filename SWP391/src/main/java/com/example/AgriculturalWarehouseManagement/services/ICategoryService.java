package com.example.AgriculturalWarehouseManagement.services;

import com.example.AgriculturalWarehouseManagement.dtos.CategoryDTO;
import com.example.AgriculturalWarehouseManagement.models.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    List<Category> findCategoryByStatusIn(List<String> status);
    Category findById(Long id) throws Exception;
    Category createCategory(CategoryDTO categoryDTO);
    Category updateCategory(Long id, CategoryDTO categoryDTO);
    void  deleteCategory(Long id);
    boolean existsByName(CategoryDTO categoryDTO);
}
