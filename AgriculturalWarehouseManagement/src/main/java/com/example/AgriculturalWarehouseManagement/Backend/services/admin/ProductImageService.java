package com.example.AgriculturalWarehouseManagement.Backend.services.admin;


import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductImage;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    @Override
    public ProductImage findFirstByProduct(Product product) {
        return productImageRepository.findAllByProduct(product)
                .stream()
                .findFirst()
                .orElse(null);
    }
}
