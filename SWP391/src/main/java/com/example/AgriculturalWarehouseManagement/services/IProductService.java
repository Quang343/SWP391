package com.example.AgriculturalWarehouseManagement.services;

import com.example.AgriculturalWarehouseManagement.dtos.*;
import com.example.AgriculturalWarehouseManagement.models.*;

import java.util.*;

public interface IProductService {
    Product createProduct(ProductDTO productDTO);
    Product updateProduct(Long id, ProductDTO productDTO);
    boolean deleteProduct(Long id);
    List<Product> findAll();
    Product findById(Long id);
    boolean existsByName(String name);

    ProductImage createProductImage(Long productId, ProductImageDTO productImageDTO) throws Exception;
}
