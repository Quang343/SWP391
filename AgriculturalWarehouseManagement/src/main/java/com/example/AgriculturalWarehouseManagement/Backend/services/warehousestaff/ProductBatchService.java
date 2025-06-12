package com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.ProductBatchDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.ProductBatchMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductBatch;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductBatchRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductBatchService {
    @Autowired
    private ProductBatchRepository repository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ProductBatchMapper mapper;

    public ProductBatchDTO create(ProductBatchDTO dto) {
        if (dto.getProductDetailID() == null) {
            throw new RuntimeException("ProductDetail ID is required");
        }
        ProductBatch entity = mapper.productBatchDTOToProductBatch(dto); // Mapper sẽ gán ProductDetail
        return mapper.productBatchToProductBatchDTO(repository.save(entity));
    }

    public ProductBatchDTO update(Integer id, ProductBatchDTO dto) {
        ProductBatch entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductBatch not found"));

        if (dto.getProductDetailID() == null) {
            throw new RuntimeException("ProductDetail ID is required");
        }
        ProductBatch updatedEntity = mapper.productBatchDTOToProductBatch(dto); // Mapper sẽ gán ProductDetail
        entity.setProductDetail(updatedEntity.getProductDetail());
        entity.setManufactureDate(updatedEntity.getManufactureDate());
        entity.setQuantity(updatedEntity.getQuantity());

        return mapper.productBatchToProductBatchDTO(repository.save(entity));
    }

    public void delete(Integer id) {
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