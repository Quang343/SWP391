package com.example.AgriculturalWarehouseManagement.Backend.services.admin;



import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.models.Gallery;

import java.util.List;

public interface IProductImageService {
    List<Gallery> findAllByProduct(Product product);
    List<Gallery> findAll();
    void deleteAllByProduct(Product product);
}
