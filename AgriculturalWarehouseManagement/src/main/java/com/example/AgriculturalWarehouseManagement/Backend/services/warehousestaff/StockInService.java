package com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.ProductBatchDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockInDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockInDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.StockInMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockIn;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockInDetail;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductBatchRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.StockInDetailRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.StockInRepository;
import org.hibernate.engine.jdbc.batch.spi.Batch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StockInService {
    @Autowired
    private StockInRepository stockInRepository;

    @Autowired
    private StockInDetailRepository stockInDetailRepository;

    @Autowired
    private ProductBatchRepository batchRepository;

    private final RestTemplate restTemplate;

    @Autowired
    public StockInService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public StockInDTO createStockIn(StockInDTO stockInDTO) {
        StockIn stockIn = StockInMapper.toEntity(stockInDTO);
        stockIn = stockInRepository.save(stockIn);
        return StockInMapper.toDTO(stockIn);
    }

    public Page<StockIn> getPaginatedStockIn(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StockIn> stockInPage = stockInRepository.findPaginatedStockIn(pageable);
        // Debug output
        System.out.println("Debug - Page: " + page + ", Size: " + size +
                ", Total Pages: " + stockInPage.getTotalPages() +
                ", Total Elements: " + stockInPage.getTotalElements());

        return stockInPage;
    }

    public List<StockInDTO> getAllStockIns() {
        return stockInRepository.findAll().stream()
                .map(StockInMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StockInDTO getStockInById(Integer id) {
        StockIn stockIn = stockInRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StockIn not found"));
        return StockInMapper.toDTO(stockIn);
    }

    public void deleteStockIn(Integer id) {
        stockInRepository.deleteById(id);
    }

    public StockInDTO updateStockIn(Integer id, StockInDTO stockInDTO) {
        StockIn existingStockIn = stockInRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StockIn not found with id: " + id));

        // Sử dụng mapper để cập nhật entity từ DTO
        StockIn updatedStockIn = StockInMapper.toEntity(stockInDTO);
        updatedStockIn.setId(existingStockIn.getId()); // Giữ nguyên ID để tránh tạo mới

        // Lưu và chuyển đổi lại thành DTO
        updatedStockIn = stockInRepository.save(updatedStockIn);
        return StockInMapper.toDTO(updatedStockIn);
    }

    public StockIn saveStockInWithDetailsUsingOldApis(StockIn stockIn, List<StockInDetailDTO> details, List<ProductBatchDTO> newBatches) {
        // Lưu StockIn trước để lấy ID
        StockIn savedStockIn = stockInRepository.save(stockIn);
        int stockInId = savedStockIn.getId();

        // Tạo ánh xạ batchID mới từ newBatches
        List<Integer> newBatchIds = new ArrayList<>();
        if (newBatches != null) {
            for (ProductBatchDTO newBatch : newBatches) {
                if (newBatch.getProductDetailID() == null || newBatch.getManufactureDate() == null || newBatch.getImportedQuantity() == null) {
                    throw new IllegalArgumentException("Thông tin batch mới không đầy đủ: productDetailID, manufactureDate, và importedQuantity là bắt buộc.");
                }
                if (newBatch.getSoldQuantity() == null) {
                    newBatch.setSoldQuantity(0);
                }

                try {
                    ResponseEntity<ProductBatchDTO> batchResponse = restTemplate.postForEntity(
                            "http://localhost:8080/AgriculturalWarehouseManagementApplication/api/product-batches",
                            newBatch,
                            ProductBatchDTO.class
                    );
                    ProductBatchDTO createdBatch = batchResponse.getBody();
                    if (createdBatch == null || createdBatch.getBatchID() == null) {
                        throw new IllegalArgumentException("Tạo Batch mới thất bại: Response không hợp lệ.");
                    }
                    newBatchIds.add(createdBatch.getBatchID());
                } catch (HttpClientErrorException e) {
                    throw new IllegalArgumentException("Lỗi khi tạo Batch mới: " + e.getMessage() + ". Kiểm tra API /api/product-batches và dữ liệu gửi đi: " + newBatch);
                }
            }
        }

        // Lưu trữ các BatchID đã sử dụng
        Set<Integer> usedBatchIds = new HashSet<>();
        if (details != null) {
            int newBatchIndex = 0;
            for (StockInDetailDTO detailDTO : details) {
                Integer batchId;
                if (detailDTO.getBatchID() != null) {
                    // Sử dụng Batch hiện có
                    if (!usedBatchIds.contains(detailDTO.getBatchID())) {
                        batchId = detailDTO.getBatchID();
                        usedBatchIds.add(batchId);
                    } else {
                        throw new IllegalArgumentException("Batch ID " + detailDTO.getBatchID() + " đã được sử dụng.");
                    }
                } else if (newBatchIndex < newBatchIds.size()) {
                    // Sử dụng batchID mới tạo
                    batchId = newBatchIds.get(newBatchIndex++);
                    usedBatchIds.add(batchId);
                } else {
                    throw new IllegalArgumentException("Không đủ batch mới cho các chi tiết nhập kho.");
                }

                // Tạo StockInDetail bằng API cũ
                StockInDetailDTO detailToSave = new StockInDetailDTO();
                detailToSave.setStockInID(stockInId);
                detailToSave.setQuantity(detailDTO.getQuantity());
                detailToSave.setUnitPrice(detailDTO.getUnitPrice());
                detailToSave.setBatchID(batchId);
                restTemplate.postForEntity(
                        "http://localhost:8080/AgriculturalWarehouseManagementApplication/api/stockindetails",
                        detailToSave,
                        StockInDetailDTO.class
                );
            }
        }

        return stockInRepository.save(savedStockIn); // Cập nhật lại nếu cần
    }

    public List<Map<String, Object>> getTotalSpentByMonth() {
        return stockInDetailRepository.findTotalSpentByMonth()
                .stream()
                .map(result -> {
                    Integer year = (Integer) result[0];
                    Integer month = (Integer) result[1];
                    BigDecimal totalSpent = new BigDecimal(result[2].toString());

                    String formattedMonth = String.format("%d-%02d", year, month);

                    Map<String, Object> map = new HashMap<>();
                    map.put("month", formattedMonth);
                    map.put("totalSpent", totalSpent);
                    return map;
                })
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getTotalQuantityByMonth() {
        List<Object[]> results = stockInDetailRepository.findTotalQuantityMonth();
        return results.stream()
                .map(result -> {
                    Integer year = (Integer) result[0];
                    Integer month = (Integer) result[1];
                    Long totalQuantity = (Long) result[2];
                    String formattedMonth = String.format("%d-%02d", year, month);
                    Map<String, Object> map = new HashMap<>();
                    map.put("month", formattedMonth);
                    map.put("totalQuantity", totalQuantity);
                    return map;
                })
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getTop4Suppliers() {
        try {
            Pageable top5 = PageRequest.of(0, 5);
            List<Object[]> results = stockInDetailRepository.findTopSuppliers(top5);

            return results.stream().map(obj -> {
                Map<String, Object> map = new HashMap<>();
                map.put("supplierID", obj[0]);
                map.put("totalQuantity", obj[1]);
                return map;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
