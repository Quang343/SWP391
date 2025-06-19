package com.example.AgriculturalWarehouseManagement.Backend.services.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.CategoryUsersResponse;
import com.example.AgriculturalWarehouseManagement.Backend.models.Category;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryUsersService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryUsersResponse> getAllListCategory() {

        if (categoryRepository.findAll().isEmpty()) {
            return new ArrayList<>();
        }

        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryUsersResponse> categoryUsersResponses = new ArrayList<>();

        for (Category category : categoryList) {
            CategoryUsersResponse categoryUsersResponse = new CategoryUsersResponse();
            categoryUsersResponse.setCategoryId(category.getCategoryId());
            categoryUsersResponse.setCategoryName(category.getName());
            categoryUsersResponse.setDescription(category.getDescription());
            categoryUsersResponse.setStatus(category.getStatus());
            categoryUsersResponse.setImageUrl(category.getImageUrl());
            categoryUsersResponses.add(categoryUsersResponse);
        }

        return categoryUsersResponses;
    }
}
