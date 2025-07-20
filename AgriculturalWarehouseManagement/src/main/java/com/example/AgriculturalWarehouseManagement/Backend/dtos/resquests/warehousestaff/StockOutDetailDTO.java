package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff;

import lombok.*;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockOutDetailDTO {
    private Integer id;
    private Integer stockOutID;
    private Integer orderDetailID;
    private Integer batchID;
    private Integer quantity;
}
