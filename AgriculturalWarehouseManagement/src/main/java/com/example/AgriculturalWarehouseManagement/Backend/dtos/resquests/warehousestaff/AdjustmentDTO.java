package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdjustmentDTO {
    private Integer id;
    private Integer batchId;
    private Integer quantity;
    private LocalDateTime adjustDate;
    private String reason;
    private String adjustmentType;
}