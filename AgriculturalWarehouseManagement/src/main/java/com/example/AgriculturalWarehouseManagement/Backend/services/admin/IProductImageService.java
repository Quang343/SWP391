package com.example.AgriculturalWarehouseManagement.Backend.services.admin;



import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductImage;

import java.util.List;

public interface IProductImageService {
    List<ProductImage> findAllByProduct(Product product);
    List<ProductImage> findAll();
    void deleteAllByProduct(Product product);
}
