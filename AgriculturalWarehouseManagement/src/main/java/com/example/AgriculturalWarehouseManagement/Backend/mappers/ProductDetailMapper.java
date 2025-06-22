package com.example.AgriculturalWarehouseManagement.Backend.mappers;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.ProductDetailDTO;

import com.example.AgriculturalWarehouseManagement.Backend.models.CategoryWeight;
import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductDetail;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.CategoryWeightRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailMapper {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryWeightRepository categoryWeightRepository;

    public ProductDetail productDetailDTOToProductDetail(ProductDetailDTO dto) {
        ProductDetail entity = new ProductDetail();
        entity.setProductDetailId(dto.getProductDetailID());

        // Resolve Product
        Product product = productRepository.findById(dto.getProductID().longValue())
                .orElseThrow(() -> new RuntimeException("Product with ID " + dto.getProductID() + " not found"));
        entity.setProductID(product);

        // ✅ Resolve CategoryWeight by ID
        if (dto.getCategoryWeight() != null) {
            CategoryWeight categoryWeight = categoryWeightRepository.findById(Math.toIntExact(dto.getCategoryWeight()))
                    .orElseThrow(() -> new RuntimeException("CategoryWeight not found"));
            entity.setCategoryWeight(categoryWeight);
        }

        entity.setPrice(dto.getPrice());
        entity.setExpiry(dto.getExpiry());
        entity.setDetailName(dto.getDetailName());

        return entity;
    }

    public ProductDetailDTO productDetailToProductDetailDTO(ProductDetail entity) {
        ProductDetailDTO dto = new ProductDetailDTO();
        dto.setProductDetailID(entity.getProductDetailId());
        dto.setProductID(entity.getProductID() != null ? entity.getProductID().getId().intValue() : null);

        // ✅ Trả về ID thay vì weight
        dto.setCategoryWeight(Long.valueOf(entity.getCategoryWeight() != null ? entity.getCategoryWeight().getId() : null));

        dto.setPrice(entity.getPrice());
        dto.setExpiry(entity.getExpiry());
        dto.setDetailName(entity.getDetailName());

        return dto;
    }

    public void updateProductDetailFromDTO(ProductDetailDTO dto, ProductDetail entity) {
        if (dto.getProductID() != null) {
            Product product = productRepository.findById(dto.getProductID().longValue())
                    .orElseThrow(() -> new RuntimeException("Product with ID " + dto.getProductID() + " not found"));
            entity.setProductID(product);
        }

        // ✅ Gán lại CategoryWeight nếu có ID
        if (dto.getCategoryWeight() != null) {
            CategoryWeight categoryWeight = categoryWeightRepository.findById(Math.toIntExact(dto.getCategoryWeight()))
                    .orElseThrow(() -> new RuntimeException("CategoryWeight with  not found"));
            entity.setCategoryWeight(categoryWeight);
        }

        if (dto.getPrice() != null) entity.setPrice(dto.getPrice());
        if (dto.getExpiry() != null) entity.setExpiry(dto.getExpiry());
        if (dto.getDetailName() != null) entity.setDetailName(dto.getDetailName());
    }
}
