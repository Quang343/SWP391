package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.models.StockOutStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDateTime stockOutDate;
    private String note;
    private StockOutStatus status;
}
