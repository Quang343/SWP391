package com.example.AgriculturalWarehouseManagement.Backend.repositorys;


import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.models.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<Gallery, Long> {
    List<Gallery> findAllByProduct(Product product);
    void deleteAllByProduct(Product product);
    List<Gallery> findByProductId(Long productId);
}
