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

    @Size(max=500)
    private String description;

//    @NotNull(message = "Warehouse is required")
//    private Long warehouseId;

    @NotNull
    private String status;
}
