package com.example.AgriculturalWarehouseManagement.Backend.services.admin;



import com.example.AgriculturalWarehouseManagement.Backend.models.Gallery;
import com.example.AgriculturalWarehouseManagement.Backend.models.Product;

import java.util.List;

public interface IProductImageService {
    List<Gallery> findAllByProduct(Product product);
    List<Gallery> findAll();
    void deleteAllByProduct(Product product);
    Gallery findFirstByProduct(Product product);
}
