package com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockInDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.StockInDetailMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockIn;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockInDetail;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.StockInDetailRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.StockInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class StockInDetailService {
    @Autowired
    private StockInDetailRepository stockInDetailRepository;
    @Autowired
    private StockInRepository stockInRepository; // Để kiểm tra StockIn tồn tại

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

    public void deleteStockInDetail(Integer id) {
        stockInDetailRepository.deleteById(id);
    }
}
