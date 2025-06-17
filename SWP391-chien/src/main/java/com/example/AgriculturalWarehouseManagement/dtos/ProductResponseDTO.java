package com.example.AgriculturalWarehouseManagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private String name;
    private String description;
    private String status;
    private CategoryResponseDTO category;
}
