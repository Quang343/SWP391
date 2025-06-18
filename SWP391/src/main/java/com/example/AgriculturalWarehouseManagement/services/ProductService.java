package com.example.AgriculturalWarehouseManagement.services;

import com.example.AgriculturalWarehouseManagement.dtos.ProductDTO;
import com.example.AgriculturalWarehouseManagement.dtos.ProductImageDTO;
import com.example.AgriculturalWarehouseManagement.models.*;
import com.example.AgriculturalWarehouseManagement.repositories.CategoryRepository;
import com.example.AgriculturalWarehouseManagement.repositories.ProductImageRepository;
import com.example.AgriculturalWarehouseManagement.repositories.ProductRepository;
import com.example.AgriculturalWarehouseManagement.repositories.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final WarehouseRepository warehouseRepository;
    private final ProductImageRepository productImageRepository;

    @Override
    public Product createProduct(ProductDTO productDTO) {
        //*
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        //*
        Warehouse warehouse = warehouseRepository.findById(1L).orElse(null);

        Product product = Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .category(category)
                .status(ProductStatus.valueOf(productDTO.getStatus()))
                .build();
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductDTO productDTO) {
        Product product = findById(id);
        product.setName(productDTO.getName().trim());
        product.setDescription(productDTO.getDescription());
        product.setStatus(ProductStatus.valueOf(productDTO.getStatus()));

        Warehouse warehouse = warehouseRepository.findById(1L).orElse(null);
        return productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setStatus(ProductStatus.INACTIVE);
            productRepository.save(product);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByNameIgnoreCase(name.trim());
    }

    @Override
    public ProductImage createProductImage(Long productId, ProductImageDTO productImageDTO) throws Exception {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new Exception
                        ("Product not found with id: " + productId));
        ProductImage productImage = ProductImage.builder()
                .product(product)
                .imageUrl(productImageDTO.getImageUrl())
                .build();
        //không cho insert quá 5 ảnh cho 1 sản phẩm
        int size = productImageRepository.findByProductId(productId).size();
        if (size >= ProductImage.MAXIMUM_IMAGES_PER_PRODUCT) {
            throw new Exception("Number of images must be <= " + ProductImage.MAXIMUM_IMAGES_PER_PRODUCT);
        }
        return productImageRepository.save(productImage);
    }

    //    chiến_seller
    public void save(Product product) {
        productRepository.save(product);
    }

}
