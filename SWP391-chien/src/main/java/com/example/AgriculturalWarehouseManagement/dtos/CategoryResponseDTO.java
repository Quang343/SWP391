package com.example.AgriculturalWarehouseManagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String status;
}

