package com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockInDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockInDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.StockInDetailMapper;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.StockInMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockIn;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockInDetail;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.StockInDetailRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.StockInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockInService {
    @Autowired
    private StockInRepository stockInRepository;

    public StockInDTO createStockIn(StockInDTO stockInDTO) {
        StockIn stockIn = StockInMapper.toEntity(stockInDTO);
        stockIn = stockInRepository.save(stockIn);
        return StockInMapper.toDTO(stockIn);
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
}
