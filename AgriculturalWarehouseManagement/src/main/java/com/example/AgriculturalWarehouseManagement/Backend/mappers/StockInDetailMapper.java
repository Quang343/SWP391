package com.example.AgriculturalWarehouseManagement.Backend.mappers;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockInDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductBatch;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductDetail;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockIn;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockInDetail;

public class StockInDetailMapper {
    public static StockInDetailDTO toDTO(StockInDetail stockindetail) {
        if (stockindetail == null) {
            return null;
        }
        StockInDetailDTO dto = new StockInDetailDTO();
        dto.setId(stockindetail.getId());
        if (stockindetail.getStockInID() != null) {
            dto.setStockInID(stockindetail.getStockInID().getId());
        }
        if (stockindetail.getProductDetailID() != null) {
            dto.setProductDetailID(stockindetail.getProductDetailID().getProductDetailId());
        }
        if (stockindetail.getBatchID() != null) {
            dto.setBatchID(stockindetail.getBatchID().getBatchId());
        }
        dto.setQuantity(stockindetail.getQuantity());
        dto.setUnitPrice(stockindetail.getUnitPrice());
        return dto;
    }

    public static StockInDetail toEntity(StockInDetailDTO stockindetailDTO) {
        if (stockindetailDTO == null) {
            return null;
        }
        StockInDetail stockindetail = new StockInDetail();
        stockindetail.setId(stockindetailDTO.getId());
        stockindetail.setQuantity(stockindetailDTO.getQuantity());
        stockindetail.setUnitPrice(stockindetailDTO.getUnitPrice());

        // Gán StockIn entity từ ID
        if (stockindetailDTO.getStockInID() != null) {
            StockIn stockIn = new StockIn();
            stockIn.setId(stockindetailDTO.getStockInID());
            stockindetail.setStockInID(stockIn);
        }

        // Gán ProductDetail entity từ ID
        if (stockindetailDTO.getProductDetailID() != null) {
            ProductDetail productDetail = new ProductDetail();
            productDetail.setProductDetailId(stockindetailDTO.getProductDetailID());
            stockindetail.setProductDetailID(productDetail);
        }

        // Gán ProductBatch entity từ ID
        if (stockindetailDTO.getBatchID() != null) {
            ProductBatch batch = new ProductBatch();
            batch.setBatchId(stockindetailDTO.getBatchID());
            stockindetail.setBatchID(batch);
        }

        return stockindetail;
    }
}
