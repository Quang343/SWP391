package com.example.AgriculturalWarehouseManagement.services;

import com.example.AgriculturalWarehouseManagement.dtos.ProductDTO;
import com.example.AgriculturalWarehouseManagement.models.Product;

import java.util.List;

public interface IProductService {
    Product createProduct(ProductDTO productDTO);
    Product updateProduct(Long id, ProductDTO productDTO);
    boolean deleteProduct(Long id);
    List<Product> findAll();
    Product findById(Long id);
    boolean existsByName(String name);
}
