package com.example.AgriculturalWarehouseManagement.services;

import com.example.AgriculturalWarehouseManagement.dtos.CategoryDTO;
import com.example.AgriculturalWarehouseManagement.models.Category;
import com.example.AgriculturalWarehouseManagement.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findCategoryByStatusIn(List<String> status) {
        return categoryRepository.findCategoryByStatusIn(status);
    }

    @Override
    public Category findById(Long id) throws Exception {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found category with id: " + id));
    }

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .status(categoryDTO.getStatus())
                .imageUrl(categoryDTO.getImageUrl())
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public boolean existsByNameIgnoreCase(String name) {
        return categoryRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public Category updateCategory(Long id, CategoryDTO categoryDTO) throws Exception {
        Category category = findById(id);
        if(!category.getName().equalsIgnoreCase(categoryDTO.getName()) &&
                categoryRepository.existsByNameIgnoreCase(categoryDTO.getName())){
            throw new Exception("Category name already exists");
        }

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setStatus(categoryDTO.getStatus());
        category.setImageUrl(categoryDTO.getImageUrl());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        try {
            Category category = findById(id);
            category.setStatus("DELETED");
            categoryRepository.save(category);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
