package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff;

import lombok.*;

import java.time.LocalDate;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockOutDTO {
    private Integer id;
    private Integer warehouseID;
    private Integer orderID;
    private LocalDate stockOutDate;
    private String note;
}
