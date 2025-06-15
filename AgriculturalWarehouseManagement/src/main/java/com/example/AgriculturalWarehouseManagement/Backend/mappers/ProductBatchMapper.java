package com.example.AgriculturalWarehouseManagement.Backend.mappers;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.ProductBatchDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductBatch;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductDetail;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductDetailRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductBatchMapper {
    @Autowired
    private ProductDetailRepository productDetailRepository;

    public ProductBatchDTO productBatchToProductBatchDTO(ProductBatch productBatch) {
        ProductBatchDTO dto = new ProductBatchDTO();
        dto.setBatchID(productBatch.getBatchId());
        // Khởi tạo ProductDetail nếu không null
        if (productBatch.getProductDetail() != null) {
            Hibernate.initialize(productBatch.getProductDetail());
            dto.setProductDetailID(productBatch.getProductDetail().getProductDetailId());
        }
        dto.setManufactureDate(productBatch.getManufactureDate());
        dto.setImportedQuantity(productBatch.getImportedQuantity());
        dto.setSoldQuantity(productBatch.getSoldQuantity());
        return dto;
    }

    public ProductBatch productBatchDTOToProductBatch(ProductBatchDTO dto) {
        ProductBatch productBatch = new ProductBatch();
        productBatch.setBatchId(dto.getBatchID());
        if (dto.getProductDetailID() != null) {
            ProductDetail productDetail = productDetailRepository.findById(dto.getProductDetailID())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy ProductDetail với ID: " + dto.getProductDetailID()));
            productBatch.setProductDetail(productDetail);
        }
        productBatch.setManufactureDate(dto.getManufactureDate());
        productBatch.setImportedQuantity(dto.getImportedQuantity());
        productBatch.setSoldQuantity(dto.getSoldQuantity() != null ? dto.getSoldQuantity() : 0); // Đảm bảo soldQuantity không null
        return productBatch;
    }
}
