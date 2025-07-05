package com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockInDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.StockInDetailMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductBatch;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockIn;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockInDetail;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductBatchRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.StockInDetailRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.StockInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StockInDetailService {

    @Autowired
    private StockInDetailRepository stockInDetailRepository;

    @Autowired
    private StockInRepository stockInRepository; // Để kiểm tra StockIn tồn tại

    @Autowired
    private ProductBatchRepository productBatchRepository;


    public StockInDetailDTO createStockInDetail(StockInDetailDTO stockInDetailDTO) {
        StockInDetail stockInDetail = StockInDetailMapper.toEntity(stockInDetailDTO);
        // Kiểm tra StockIn tồn tại trước khi lưu
        if (stockInDetailDTO.getStockInID() != null) {
            stockInRepository.findById(stockInDetailDTO.getStockInID())
                    .orElseThrow(() -> new RuntimeException("StockIn not found for ID: " + stockInDetailDTO.getStockInID()));
        }
        stockInDetail = stockInDetailRepository.save(stockInDetail);
        return StockInDetailMapper.toDTO(stockInDetail);
    }

    public List<StockInDetailDTO> getAllStockInDetails() {
        return stockInDetailRepository.findAll().stream()
                .map(StockInDetailMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<StockInDetailDTO> getStockInDetailsByStockInId(Integer stockInId) {
        StockIn stockIn = stockInRepository.findById(stockInId)
                .orElseThrow(() -> new RuntimeException("StockIn not found"));
        return stockInDetailRepository.findByStockInID(stockIn).stream()
                .map(StockInDetailMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StockInDetailDTO getStockInDetailById(Integer id) {
        StockInDetail stockInDetail = stockInDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StockInDetail not found"));
        return StockInDetailMapper.toDTO(stockInDetail);
    }


    public List<StockInDetailDTO> findByBatchId(Integer batchId) {
        if (batchId == null || batchId <= 0) {
            throw new IllegalArgumentException("Invalid Batch ID");
        }
        ProductBatch batch = productBatchRepository.findById(batchId)
                .orElseThrow(() -> new IllegalArgumentException("ProductBatch not found with ID: " + batchId));
        return stockInDetailRepository.findByBatchID(batch).stream()
                .map(StockInDetailMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StockInDetailDTO updateStockInDetail(Integer id, StockInDetailDTO stockInDetailDTO) {
        // Validate input ID
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid StockInDetail ID");
        }

        // Find existing StockInDetail
        StockInDetail existingStockInDetail = stockInDetailRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("StockInDetail not found with id: " + id));

        // Validate StockInID
        if (stockInDetailDTO.getStockInID() == null) {
            throw new IllegalArgumentException("StockIn ID is required");
        }
        StockIn stockIn = stockInRepository.findById(stockInDetailDTO.getStockInID())
                .orElseThrow(() -> new IllegalArgumentException("StockIn not found with id: " + stockInDetailDTO.getStockInID()));
        existingStockInDetail.setStockInID(stockIn);

        // Validate BatchID
        if (stockInDetailDTO.getBatchID() != null) {
            ProductBatch batch = productBatchRepository.findById(stockInDetailDTO.getBatchID())
                    .orElseThrow(() -> new IllegalArgumentException("ProductBatch not found with id: " + stockInDetailDTO.getBatchID()));
            existingStockInDetail.setBatchID(batch);
        } else {
            existingStockInDetail.setBatchID(null);
        }

        // Validate quantity
        if (stockInDetailDTO.getQuantity() == null || stockInDetailDTO.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        existingStockInDetail.setQuantity(stockInDetailDTO.getQuantity());

        // Validate unit price
        if (stockInDetailDTO.getUnitPrice() != null && stockInDetailDTO.getUnitPrice() <= 0) {
            throw new IllegalArgumentException("Unit price must be positive");
        }
        existingStockInDetail.setUnitPrice(stockInDetailDTO.getUnitPrice());

        // Save updated entity
        StockInDetail updatedStockInDetail = stockInDetailRepository.save(existingStockInDetail);

        return StockInDetailMapper.toDTO(updatedStockInDetail);
    }


    public void deleteStockInDetail(Integer id) {
        stockInDetailRepository.deleteById(id);
    }
}
