package com.example.AgriculturalWarehouseManagement.services;

import com.example.AgriculturalWarehouseManagement.models.ProductImage;

import java.util.List;

public interface IProductImageService {
    List<ProductImage> findByProductId(Long productId);
    List<ProductImage> findAll();
}
