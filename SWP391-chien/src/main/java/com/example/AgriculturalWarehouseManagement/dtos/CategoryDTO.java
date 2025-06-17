package com.example.AgriculturalWarehouseManagement.dtos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    @NotBlank(message = "Category name is required!")
    private String name;

    private String description;

    @NotBlank
    private String status;
}
