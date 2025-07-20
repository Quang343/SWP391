package com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockOutDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.StockOutDetailMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.OrderDetail;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductBatch;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockOut;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockOutDetail;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.OrderDetailRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductBatchRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.StockOutDetailRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.StockOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockOutDetailService {
    @Autowired
    private StockOutDetailRepository stockOutDetailRepository;

    @Autowired
    private StockOutRepository stockOutRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductBatchRepository productBatchRepository;

    @Autowired
    private StockOutDetailMapper stockOutDetailMapper;

    public List<StockOutDetailDTO> getAllStockOutDetails() {
        return stockOutDetailRepository.findAll().stream()
                .map(stockOutDetailMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StockOutDetailDTO getStockOutDetailById(Integer id) {
        StockOutDetail stockOutDetail = stockOutDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StockOutDetail not found"));
        return stockOutDetailMapper.toDTO(stockOutDetail);
    }

    public List<StockOutDetailDTO> getStockOutDetailsByStockOutId(Integer stockOutID) {
        return stockOutDetailRepository.findByStockOutID_Id(stockOutID).stream()
                .map(stockOutDetailMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StockOutDetailDTO createStockOutDetail(StockOutDetailDTO stockOutDetailDTO) {
        StockOutDetail stockOutDetail = stockOutDetailMapper.toEntity(stockOutDetailDTO);
        if (stockOutDetailDTO.getStockOutID() != null) {
            StockOut stockOut = stockOutRepository.findById(stockOutDetailDTO.getStockOutID())
                    .orElseThrow(() -> new RuntimeException("StockOut not found"));
            stockOutDetail.setStockOutID(stockOut);
        }
        if (stockOutDetailDTO.getOrderDetailID() != null) {
            OrderDetail orderDetail = orderDetailRepository.findById(Long.valueOf(stockOutDetailDTO.getOrderDetailID()))
                    .orElseThrow(() -> new RuntimeException("OrderDetail not found"));
            stockOutDetail.setOrderDetailID(orderDetail);
        }
        if (stockOutDetailDTO.getBatchID() != null) {
            ProductBatch productBatch = productBatchRepository.findById(stockOutDetailDTO.getBatchID())
                    .orElseThrow(() -> new RuntimeException("ProductBatch not found"));
            stockOutDetail.setBatchID(productBatch);
        }
        stockOutDetail.setQuantity(stockOutDetailDTO.getQuantity());
        StockOutDetail savedStockOutDetail = stockOutDetailRepository.save(stockOutDetail);
        return stockOutDetailMapper.toDTO(savedStockOutDetail);
    }

    public StockOutDetailDTO updateStockOutDetail(Integer id, StockOutDetailDTO stockOutDetailDTO) {
        StockOutDetail stockOutDetail = stockOutDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StockOutDetail not found"));
        stockOutDetail.setQuantity(stockOutDetailDTO.getQuantity());
        if (stockOutDetailDTO.getStockOutID() != null) {
            StockOut stockOut = stockOutRepository.findById(stockOutDetailDTO.getStockOutID())
                    .orElseThrow(() -> new RuntimeException("StockOut not found"));
            stockOutDetail.setStockOutID(stockOut);
        }
        if (stockOutDetailDTO.getOrderDetailID() != null) {
            OrderDetail orderDetail = orderDetailRepository.findById(Long.valueOf(stockOutDetailDTO.getOrderDetailID()))
                    .orElseThrow(() -> new RuntimeException("OrderDetail not found"));
            stockOutDetail.setOrderDetailID(orderDetail);
        }
        if (stockOutDetailDTO.getBatchID() != null) {
            ProductBatch productBatch = productBatchRepository.findById(stockOutDetailDTO.getBatchID())
                    .orElseThrow(() -> new RuntimeException("ProductBatch not found"));
            stockOutDetail.setBatchID(productBatch);
        }
        StockOutDetail updatedStockOutDetail = stockOutDetailRepository.save(stockOutDetail);
        return stockOutDetailMapper.toDTO(updatedStockOutDetail);
    }

    public void deleteStockOutDetail(Integer id) {
        stockOutDetailRepository.deleteById(id);
    }
}
