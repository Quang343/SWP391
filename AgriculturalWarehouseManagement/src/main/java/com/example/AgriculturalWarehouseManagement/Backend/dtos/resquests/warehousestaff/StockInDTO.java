package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockInDTO {
    private Integer id;
    private Integer supplierID;
    private Integer warehouseID;
    private LocalDateTime stockInDate;
    private String note;
   // private List<StockInDetailDTO> details;
}
