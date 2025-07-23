package com.example.AgriculturalWarehouseManagement.Backend.mappers;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.ProductBatchDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductBatch;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductDetail;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductDetailRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductBatchMapper {
    @Autowired
    private ProductDetailRepository productDetailRepository;

    public static ProductBatchDTO productBatchToProductBatchDTO(ProductBatch productBatch) {
        ProductBatchDTO dto = new ProductBatchDTO();
        dto.setBatchID(productBatch.getBatchId());
        // Chỉ lấy productDetailId, không cần khởi tạo proxy
        dto.setProductDetailID(productBatch.getProductDetail() != null ? productBatch.getProductDetail().getProductDetailId() : null);
        dto.setManufactureDate(productBatch.getManufactureDate());
        dto.setImportedQuantity(productBatch.getImportedQuantity() != null ? productBatch.getImportedQuantity() : 0);
        dto.setSoldQuantity(productBatch.getSoldQuantity() != null ? productBatch.getSoldQuantity() : 0);
        return dto;
    }

    public ProductBatch productBatchDTOToProductBatch(ProductBatchDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("ProductBatchDTO cannot be null");
        }

        ProductBatch productBatch = new ProductBatch();
        productBatch.setBatchId(dto.getBatchID());
        if (dto.getProductDetailID() != null) {
            ProductDetail productDetail = productDetailRepository.findById(dto.getProductDetailID())
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy ProductDetail với ID: " + dto.getProductDetailID()));
            productBatch.setProductDetail(productDetail);
        }
        productBatch.setManufactureDate(dto.getManufactureDate());
        productBatch.setImportedQuantity(dto.getImportedQuantity() != null ? dto.getImportedQuantity() : 0);
        productBatch.setSoldQuantity(dto.getSoldQuantity() != null ? dto.getSoldQuantity() : 0);
        return productBatch;
    }
}
