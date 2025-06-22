package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductDetailDTO {
    private Integer productDetailID;
    private Integer productID;
    private BigDecimal price;
    private Long categoryWeight;
    private Integer expiry;
    private String detailName;


}
