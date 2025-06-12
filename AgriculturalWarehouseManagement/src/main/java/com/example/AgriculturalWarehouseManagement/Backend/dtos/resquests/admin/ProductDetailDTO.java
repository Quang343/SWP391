package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.ProductBatchDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@Setter
@Getter
public class ProductDetailDTO {
    private Integer productDetailID;
    private Integer productID;
    private BigDecimal price;
    private BigDecimal weight;
    private Integer expiry;
    private String detailName;
    private List<ProductBatchDTO> productBatch;

}
