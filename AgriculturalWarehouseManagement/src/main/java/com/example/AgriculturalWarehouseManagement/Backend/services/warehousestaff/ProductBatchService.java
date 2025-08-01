package com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.OrderDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.AdjustmentDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.ProductBatchDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.ProductBatchMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.Adjustment;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductBatch;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.AdjustmentRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.OrderDetailRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductBatchRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductDetailRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public Page<ProductBatchDTO> getPaginatedProductBatches(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductBatch> productBatchPage = repository.findPaginatedProductBatches(pageable);

        // Debug output to console
        System.out.println("Debug - Page: " + page + ", Size: " + size +
                ", Total Pages: " + productBatchPage.getTotalPages() +
                ", Total Elements: " + productBatchPage.getTotalElements());

        // Map entities to DTOs with adjustments
        return productBatchPage.map(this::mapToDTOWithAdjustments);
    }

    public List<ProductBatchDTO> getProductBatchesByProductDetailId(Long productDetailId) {
        if (productDetailId == null) {
            throw new RuntimeException("ProductDetailId cannot be null");
        }
        List<ProductBatch> batches = repository.findUnexpiredByProductDetailId(productDetailId);
        if (batches.isEmpty()) {
            throw new RuntimeException("No ProductBatches found for productDetailId: " + productDetailId);
        }
        return batches.stream()
                .map(this::mapToDTOWithAdjustments)
                .collect(Collectors.toList());
    }

    private ProductBatchDTO mapToDTOWithAdjustments(ProductBatch productBatch) {
        ProductBatchDTO dto = mapper.productBatchToProductBatchDTO(productBatch);
        List<Adjustment> adjustments = adjustmentRepository.findByBatchBatchId(productBatch.getBatchId());
        System.out.println("Debug - Batch ID: " + productBatch.getBatchId() + ", Adjustments found: " + (adjustments != null ? adjustments.size() : 0));
        int totalAdjustment = 0;
        if (adjustments != null && !adjustments.isEmpty()) {
            for (Adjustment adj : adjustments) {
                String adjustmentType = String.valueOf(adj.getAdjustmentType());
                int quantity = adj.getQuantity() != null ? adj.getQuantity() : 0;
                System.out.println("Debug - Adjustment: Batch ID: " + productBatch.getBatchId() +
                        ", Type: '" + adjustmentType + "', Quantity: " + quantity);
                if (adjustmentType.equalsIgnoreCase("Add")) {
                    totalAdjustment += quantity;
                } else if (adjustmentType.equalsIgnoreCase("Remove")) {
                    totalAdjustment -= quantity;
                } else {
                    System.out.println("Warn - Unknown adjustment type: '" + adjustmentType + "' for Batch ID: " + productBatch.getBatchId());
                }
            }
        }
        System.out.println("Debug - Batch ID: " + productBatch.getBatchId() + ", Total Adjustment: " + totalAdjustment);
        dto.setTotalAdjustment(totalAdjustment);
        return dto;
    }

    public ProductBatchDTO create(ProductBatchDTO dto) {
        validateDto(dto);
        ProductBatch entity = mapper.productBatchDTOToProductBatch(dto);
        entity.setSoldQuantity(0); // Default
        ProductBatch savedEntity = repository.save(entity);
        return mapToDTOWithAdjustments(savedEntity);
    }

    public ProductBatchDTO update(Integer id, ProductBatchDTO dto) {
        ProductBatch entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProductBatch not found with ID: " + id));
        validateDto(dto);
        ProductBatch updatedEntity = mapper.productBatchDTOToProductBatch(dto);
        entity.setProductDetail(updatedEntity.getProductDetail());
        entity.setManufactureDate(updatedEntity.getManufactureDate());
        entity.setImportedQuantity(updatedEntity.getImportedQuantity());
        ProductBatch savedEntity = repository.save(entity);
        return mapToDTOWithAdjustments(savedEntity);
    }

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

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("ProductBatch not found with ID: " + id);
        }
        repository.deleteById(id);
    }

    public Map<String, Object> getAdjustmentsByBatchId(Integer batchId) {
        if (batchId == null) {
            throw new IllegalArgumentException("Batch ID cannot be null");
        }
        List<Adjustment> adjustments = adjustmentRepository.findByBatchBatchId(batchId);
        List<AdjustmentDTO> adjustmentDTOs = adjustments.stream()
                .map(this::adjustmentToAdjustmentDTO)
                .collect(Collectors.toList());
        int totalAdjustment = adjustments.stream()
                .mapToInt(adj -> adj.getQuantity() != null ? -adj.getQuantity() : 0)
                .sum();
        int adjustmentCount = adjustments.size();
        Map<String, Object> result = new HashMap<>();
        result.put("adjustments", adjustmentDTOs);
        result.put("totalAdjustment", totalAdjustment);
        result.put("adjustmentCount", adjustmentCount);
        return result;
    }

    private AdjustmentDTO adjustmentToAdjustmentDTO(Adjustment adjustment) {
        AdjustmentDTO dto = new AdjustmentDTO();
        dto.setId(adjustment.getId());
        dto.setBatchId(adjustment.getBatch() != null ? adjustment.getBatch().getBatchId() : null);
        dto.setQuantity(adjustment.getQuantity());
        dto.setAdjustDate(adjustment.getAdjustDate());
        dto.setReason(adjustment.getReason());
        dto.setAdjustmentType(String.valueOf(adjustment.getAdjustmentType()));
        return dto;
    }

    public ProductBatchDTO findById(Integer id) {
        ProductBatch entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProductBatch not found with ID: " + id));
        return mapToDTOWithAdjustments(entity);
    }

    public List<ProductBatchDTO> findAll() {
        List<ProductBatch> entities = (List<ProductBatch>) repository.findAll();
        return entities.stream()
                .map(this::mapToDTOWithAdjustments)
                .collect(Collectors.toList());
    }

    public long countAll() {
        return repository.count();
    }

    public List<ProductBatchDTO> getTop4RemainingBatches() {
        List<ProductBatch> allBatches = (List<ProductBatch>) repository.findAll();

        return allBatches.stream()
                .map(ProductBatchMapper::productBatchToProductBatchDTO)
                .sorted(Comparator.comparing(ProductBatchDTO::getRemainingQuantity).reversed())
                .limit(4)
                .collect(Collectors.toList());
    }

    public Map<Integer, Integer> getTotalQuantityByProductDetail() {
        List<ProductBatch> allBatches = (List<ProductBatch>) repository.findAll();
        Map<Integer, Integer> totalQuantityMap = new HashMap<>();

        for (ProductBatch batch : allBatches) {
            Integer productDetailId = batch.getProductDetail().getProductDetailId(); // Assuming getProductDetailId() exists
            ProductBatchDTO dto = mapToDTOWithAdjustments(batch);
            int remainingQuantity = dto.getRemainingQuantity(); // Sử dụng getRemainingQuantity() từ DTO

            totalQuantityMap.put(productDetailId, totalQuantityMap.getOrDefault(productDetailId, 0) + remainingQuantity);
        }

        return totalQuantityMap;
    }

    public List<Map<String, Object>> getTop4ExpiringSoonBatches() {
        List<Object[]> results = repository.findTop4ExpiringSoon();
        return results.stream()
                .map(result -> {
                    ProductBatch batch = repository.findById(((Number) result[0]).intValue())
                            .orElseThrow(() -> new EntityNotFoundException("ProductBatch not found with ID: " + result[0]));
                    ProductBatchDTO dto = mapToDTOWithAdjustments(batch);
                    // Chuyển đổi java.sql.Date sang LocalDate cho expiryDate
                    java.sql.Date sqlExpiryDate = (java.sql.Date) result[3];
                    String expiryDateStr = sqlExpiryDate != null ? sqlExpiryDate.toLocalDate().toString() : null;
                    // Tạo Map để trả về dữ liệu
                    Map<String, Object> response = new HashMap<>();
                    response.put("batchID", dto.getBatchID());
                    response.put("remainingQuantity", dto.getRemainingQuantity());
                    response.put("manufactureDate", dto.getManufactureDate().toString());
                    response.put("expiryDate", expiryDateStr);
                    return response;
                })
                .collect(Collectors.toList());
    }


    public List<Map<String, Object>> getTotalQuantityByProductDetailId(Long productDetailId) {
        return orderDetailRepository.findByProductDetailIdAndOrderStatusNot(productDetailId, "CANCELLED")
                .stream()
                .collect(Collectors.groupingBy(
                        OrderDetailDTO::getProductDetailId,
                        Collectors.summingInt(OrderDetailDTO::getQuantity)))
                .entrySet().stream()
                .map(entry -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("productDetailId", entry.getKey());
                    result.put("totalQuantity", entry.getValue());
                    return result;
                })
                .collect(Collectors.toList());
    }
}