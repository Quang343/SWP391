package com.example.AgriculturalWarehouseManagement.Backend.services.admin;


import com.example.AgriculturalWarehouseManagement.Backend.models.Gallery;
import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageService implements  IProductImageService{
    private final ProductImageRepository productImageRepository;

    @Override
    public List<Gallery> findAllByProduct(Product product) {
        return productImageRepository.findAllByProduct(product);
    }

    public List<Gallery> findByProductId(Long productId) {
        return productImageRepository.findByProductId(productId);
    }

    @Override
    public List<Gallery> findAll() {
        return productImageRepository.findAll();
    }

    @Override
    public void deleteAllByProduct(Product product) {
        productImageRepository.deleteAllByProduct(product);
    }

    public void deleteById(Long id){
        productImageRepository.deleteById(id);
    }

    public Gallery findById(Long id){
        return productImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product image not found"));
    }
}
