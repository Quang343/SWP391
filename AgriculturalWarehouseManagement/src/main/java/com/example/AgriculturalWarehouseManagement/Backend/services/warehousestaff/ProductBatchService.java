package com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.AdjustmentDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.ProductBatchDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.ProductBatchMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.Adjustment;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductBatch;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.AdjustmentRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductBatchRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductDetailRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductBatchService {
    @Autowired
    private ProductBatchRepository repository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ProductBatchMapper mapper;

    @Autowired
    private AdjustmentRepository adjustmentRepository;

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
                .orElseThrow(() -> new EntityNotFoundException("ProductBatch not found with ID: " + id));
        validateDto(dto);
        ProductBatch updatedEntity = mapper.productBatchDTOToProductBatch(dto);
        entity.setProductDetail(updatedEntity.getProductDetail());
        entity.setManufactureDate(updatedEntity.getManufactureDate());
        entity.setImportedQuantity(updatedEntity.getImportedQuantity());
        return mapper.productBatchToProductBatchDTO(repository.save(entity));
    }

    // Validate DTO
    private void validateDto(ProductBatchDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("ProductBatchDTO cannot be null");
        }
        if (dto.getProductDetailID() == null) {
            throw new IllegalArgumentException("ProductDetail ID is required");
        }
        if (dto.getImportedQuantity() == null || dto.getImportedQuantity() < 1) {
            throw new IllegalArgumentException("Imported quantity must be positive and not null");
        }
        productDetailRepository.findById(dto.getProductDetailID())
                .orElseThrow(() -> new EntityNotFoundException("ProductDetail not found with ID: " + dto.getProductDetailID()));
    }

    // Xóa ProductBatch
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("ProductBatch not found with ID: " + id);
        }
        repository.deleteById(id);
    }

    public Map<String, Object> getAdjustmentsByBatchId(Integer batchId) {
        // Kiểm tra batchId không null
        if (batchId == null) {
            throw new IllegalArgumentException("Batch ID cannot be null");
        }

        // Truy vấn danh sách adjustments dựa trên batchId
        List<Adjustment> adjustments = adjustmentRepository.findByBatchBatchId(batchId);
        if (adjustments == null || adjustments.isEmpty()) {
            return new HashMap<>(); // Trả về map rỗng nếu không có dữ liệu
        }

        // Chuyển đổi sang DTO
        List<AdjustmentDTO> adjustmentDTOs = adjustments.stream()
                .map(this::adjustmentToAdjustmentDTO)
                .collect(Collectors.toList());

        // Tính tổng và đếm
        int totalAdjustment = adjustments.stream()
                .mapToInt(adj -> adj.getQuantity() != null ? adj.getQuantity() : 0)
                .sum();
        int adjustmentCount = adjustments.size();

        // Tạo map kết quả
        Map<String, Object> result = new HashMap<>();
        result.put("adjustments", adjustmentDTOs);
        result.put("totalAdjustment", totalAdjustment);
        result.put("adjustmentCount", adjustmentCount);

        return result;
    }

    // Phương thức ánh xạ từ Adjustment sang AdjustmentDTO (cần đảm bảo đầy đủ các trường)
    private AdjustmentDTO adjustmentToAdjustmentDTO(Adjustment adjustment) {
        AdjustmentDTO dto = new AdjustmentDTO();
        dto.setId(adjustment.getId());
        dto.setWarehouseId(adjustment.getWarehouse().getId()); // Đảm bảo không null nếu không có
        dto.setBatchId(adjustment.getBatch() != null ? adjustment.getBatch().getBatchId() : null); // Lấy batchId từ Batch entity
        dto.setQuantity(adjustment.getQuantity());
        dto.setAdjustDate(adjustment.getAdjustDate()); // Đảm bảo không null
        dto.setReason(adjustment.getReason()); // Đảm bảo không null
        dto.setAdjustmentType(adjustment.getAdjustmentType().name()); // Đảm bảo không null
        return dto;
    }

    // Tìm ProductBatch theo ID
    public ProductBatchDTO findById(Integer id) {
        return repository.findById(id)
                .map(mapper::productBatchToProductBatchDTO)
                .orElseThrow(() -> new EntityNotFoundException("ProductBatch not found with ID: " + id));
    }

    public List<ProductBatchDTO> findAll() {
        List<ProductBatchDTO> result = new ArrayList<>();
        Iterable<ProductBatch> entities = repository.findAll();
        for (ProductBatch batch : entities) {
            result.add(mapper.productBatchToProductBatchDTO(batch));
        }
        return result;
    }
}