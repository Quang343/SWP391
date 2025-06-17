package com.example.AgriculturalWarehouseManagement.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDTO {
    @NotBlank(message = "Warehouse name is required!")
    private String name;

    private String location;

    private String description;
}
