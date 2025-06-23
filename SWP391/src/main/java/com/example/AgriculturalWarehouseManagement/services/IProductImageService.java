package com.example.AgriculturalWarehouseManagement.services;

import com.example.AgriculturalWarehouseManagement.models.Product;
import com.example.AgriculturalWarehouseManagement.models.ProductImage;

import java.util.List;

public interface IProductImageService {
    List<ProductImage> findAllByProduct(Product product);
    List<ProductImage> findAll();
    void deleteAllByProduct(Product product);
}
