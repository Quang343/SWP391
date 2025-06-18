package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff;

import lombok.*;

import java.math.BigDecimal;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockInDetailDTO {
    private Integer id;
    private Integer stockInID;
    private Integer productDetailID;
    private Integer quantity;
    private BigDecimal unitPrice;
    private Integer batchID;
}