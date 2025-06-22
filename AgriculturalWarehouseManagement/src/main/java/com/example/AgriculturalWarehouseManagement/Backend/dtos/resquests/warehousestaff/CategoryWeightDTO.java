package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryWeightDTO {
    private Integer categoryWeightId;
    private Integer categoryId;
    private Long weight;
    private String unit;

}
