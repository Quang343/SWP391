package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    @NotBlank(message = "Category name is required!")
    private String name;

    private String description;

    @NotBlank
    private String status;

    private String imageUrl;
}
