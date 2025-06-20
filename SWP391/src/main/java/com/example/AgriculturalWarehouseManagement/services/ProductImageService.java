package com.example.AgriculturalWarehouseManagement.services;

import com.example.AgriculturalWarehouseManagement.dtos.ProductDTO;
import com.example.AgriculturalWarehouseManagement.models.Product;
import com.example.AgriculturalWarehouseManagement.models.ProductImage;
import com.example.AgriculturalWarehouseManagement.repositories.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageService implements  IProductImageService{
    private final ProductImageRepository productImageRepository;

    @Override
    public List<ProductImage> findByProductId(Long productId) {
        return productImageRepository.findByProductId(productId);
    }

    @Override
    public List<ProductImage> findAll() {
        return productImageRepository.findAll();
    }

    public void deleteById(Long id){
        productImageRepository.deleteById(id);
    }

    public ProductImage findById(Long id){
        return productImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product image not found"));
    }

}
