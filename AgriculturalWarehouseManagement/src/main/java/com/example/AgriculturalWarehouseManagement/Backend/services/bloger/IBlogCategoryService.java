package com.example.AgriculturalWarehouseManagement.Backend.services.bloger;

import com.example.AgriculturalWarehouseManagement.Backend.models.BlogCategory;

import java.util.List;

//@author: Đào Huy Hoàng

public interface IBlogCategoryService {
    BlogCategory findById(Integer id);
    List<BlogCategory> findAll();
}
