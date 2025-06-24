package com.example.AgriculturalWarehouseManagement.Backend.mappers;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockOutDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockOutDetail;
import org.springframework.stereotype.Component;

@Component
public class StockOutDetailMapper {
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
        stockOutDetail.setId(stockOutDetailDTO.getId());
        // Note: StockOut, OrderDetail, and ProductBatch entities are not set here; they are handled in the service layer
        stockOutDetail.setQuantity(stockOutDetailDTO.getQuantity());
        return stockOutDetail;
    }
}
