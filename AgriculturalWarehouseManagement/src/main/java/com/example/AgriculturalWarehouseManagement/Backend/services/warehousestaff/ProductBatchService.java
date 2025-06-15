package com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.ProductBatchDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.ProductBatchMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductBatch;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductDetail;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductBatchRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductBatchService {
    @Autowired
    private ProductBatchRepository repository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ProductBatchMapper mapper;

    // Tạo mới ProductBatch
    public ProductBatchDTO create(ProductBatchDTO dto) {
        validateDto(dto);
        ProductBatch entity = mapper.productBatchDTOToProductBatch(dto);
        entity.setSoldQuantity(0); // Mặc định
        return mapper.productBatchToProductBatchDTO(repository.save(entity));
    }

    // Cập nhật ProductBatch
    public ProductBatchDTO update(Integer id, ProductBatchDTO dto) {
        ProductBatch entity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ProductBatch not found with ID: " + id));
        validateDto(dto);
        ProductBatch updatedEntity = mapper.productBatchDTOToProductBatch(dto);
        // Cập nhật các trường, giữ soldQuantity hiện tại
        entity.setProductDetail(updatedEntity.getProductDetail());
        entity.setManufactureDate(updatedEntity.getManufactureDate());
        entity.setImportedQuantity(updatedEntity.getImportedQuantity());
        return mapper.productBatchToProductBatchDTO(repository.save(entity));
    }

    // Validate DTO
    private void validateDto(ProductBatchDTO dto) {
        if (dto.getProductDetailID() == null) {
            throw new IllegalArgumentException("ProductDetail ID is required");
        }
        if (dto.getImportedQuantity() == null || dto.getImportedQuantity() < 1) {
            throw new IllegalArgumentException("Imported quantity must be positive and not null");
        }
        productDetailRepository.findById(dto.getProductDetailID())
                .orElseThrow(() -> new NoSuchElementException("ProductDetail not found with ID: " + dto.getProductDetailID()));
    }

    // Xóa ProductBatch
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("ProductBatch not found with ID: " + id);
        }
        repository.deleteById(id);
    }

    public ProductBatchDTO findById(Integer id) {
        return repository.findById(id)
                .map(mapper::productBatchToProductBatchDTO)
                .orElseThrow(() -> new RuntimeException("ProductBatch not found"));
    }

    public List<ProductBatch> findAll() {
        return (List<ProductBatch>) repository.findAll();
    }
}