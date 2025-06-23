package com.example.AgriculturalWarehouseManagement.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @NotNull(message = "Category is required")
    private Long categoryId;

    @NotBlank(message = "Product name id required")
    private String name;

    @Size(max = 20, message = "Description must be less than 20 characters!")
    private String description;

//    @NotNull(message = "Warehouse is required")
//    private Long warehouseId;

    @NotBlank(message = "Status is required")
    private String status;
}
