package com.example.AgriculturalWarehouseManagement.Backend.mappers;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.ProductDetailDTO;

import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductDetail;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductDetailMapper {

    @Autowired
    private ProductRepository productRepository;

    public ProductDetail productDetailDTOToProductDetail(ProductDetailDTO dto) {
        ProductDetail entity = new ProductDetail();
        entity.setProductDetailId(dto.getProductDetailID());

        // Resolve Product from productID
        Product product = productRepository.findById(dto.getProductID().longValue())
                .orElseThrow(() -> new RuntimeException("Product with ID " + dto.getProductID() + " not found"));
        entity.setProductID(product);

        entity.setPrice(dto.getPrice());
        entity.setWeight(dto.getWeight());
        entity.setExpiry(dto.getExpiry());
        entity.setDetailName(dto.getDetailName());
        return entity;
    }

    public ProductDetailDTO productDetailToProductDetailDTO(ProductDetail entity) {
        ProductDetailDTO dto = new ProductDetailDTO();
        dto.setProductDetailID(entity.getProductDetailId());
        dto.setProductID(entity.getProductID() != null ? entity.getProductID().getId().intValue() : null);
        dto.setPrice(entity.getPrice());
        dto.setWeight(entity.getWeight());
        dto.setExpiry(entity.getExpiry());
        dto.setDetailName(entity.getDetailName());
        return dto;
    }

    // Update existing ProductDetail from DTO
    public void updateProductDetailFromDTO(ProductDetailDTO dto, ProductDetail entity) {
        if (dto.getProductID() != null) {
            Product product = productRepository.findById(dto.getProductID().longValue())
                    .orElseThrow(() -> new RuntimeException("Product with ID " + dto.getProductID() + " not found"));
            entity.setProductID(product);
        }
        if (dto.getPrice() != null) entity.setPrice(dto.getPrice());
        if (dto.getWeight() != null) entity.setWeight(dto.getWeight());
        if (dto.getExpiry() != null) entity.setExpiry(dto.getExpiry());
        if (dto.getDetailName() != null) entity.setDetailName(dto.getDetailName());
    }
}
