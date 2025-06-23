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
    public List<ProductImage> findAllByProduct(Product product) {
        return productImageRepository.findAllByProduct(product);
    }

    @Override
    public List<ProductImage> findAll() {
        return productImageRepository.findAll();
    }

    @Override
    public void deleteAllByProduct(Product product) {
        productImageRepository.deleteAllByProduct(product);
    }
}
