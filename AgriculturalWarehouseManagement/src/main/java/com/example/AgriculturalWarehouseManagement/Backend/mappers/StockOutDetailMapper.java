package com.example.AgriculturalWarehouseManagement.Backend.mappers;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockOutDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.OrderDetail;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductBatch;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockOut;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockOutDetail;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.OrderDetailRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductBatchRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.StockOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockOutDetailMapper {

    @Autowired
    private StockOutRepository stockOutRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductBatchRepository productBatchRepository;

    public StockOutDetailDTO toDTO(StockOutDetail stockOutDetail) {
        if (stockOutDetail == null) {
            return null;
        }
        StockOutDetailDTO stockOutDetailDTO = new StockOutDetailDTO();
        stockOutDetailDTO.setId(stockOutDetail.getId());
        stockOutDetailDTO.setStockOutID(stockOutDetail.getStockOutID() != null ? stockOutDetail.getStockOutID().getId() : null);
        stockOutDetailDTO.setOrderDetailID(Math.toIntExact(stockOutDetail.getOrderDetailID() != null ? stockOutDetail.getOrderDetailID().getId() : null));
        stockOutDetailDTO.setBatchID(stockOutDetail.getBatchID() != null ? stockOutDetail.getBatchID().getBatchId() : null);
        stockOutDetailDTO.setQuantity(stockOutDetail.getQuantity());
        return stockOutDetailDTO;
    }

    public StockOutDetail toEntity(StockOutDetailDTO stockOutDetailDTO) {
        if (stockOutDetailDTO == null) {
            return null;
        }
        StockOutDetail stockOutDetail = new StockOutDetail();
        // Set StockOut entity
        if (stockOutDetailDTO.getStockOutID() != null) {
            StockOut stockOut = stockOutRepository.findById(stockOutDetailDTO.getStockOutID())
                    .orElseThrow(() -> new RuntimeException("StockOut not found for ID: " + stockOutDetailDTO.getStockOutID()));
            stockOutDetail.setStockOutID(stockOut);
        }
        // Set OrderDetail entity
        if (stockOutDetailDTO.getOrderDetailID() != null) {
            OrderDetail orderDetail = orderDetailRepository.findById(Long.valueOf(stockOutDetailDTO.getOrderDetailID()))
                    .orElseThrow(() -> new RuntimeException("OrderDetail not found for ID: " + stockOutDetailDTO.getOrderDetailID()));
            stockOutDetail.setOrderDetailID(orderDetail);
        }
        // Set ProductBatch entity
        if (stockOutDetailDTO.getBatchID() != null) {
            ProductBatch batch = productBatchRepository.findById(stockOutDetailDTO.getBatchID())
                    .orElseThrow(() -> new RuntimeException("ProductBatch not found for ID: " + stockOutDetailDTO.getBatchID()));
            stockOutDetail.setBatchID(batch);
        }
        stockOutDetail.setQuantity(stockOutDetailDTO.getQuantity());
        // Note: id is not set, as it is auto-generated
        return stockOutDetail;
    }
}
