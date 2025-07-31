package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ProductDTO {

    @NotNull(message = "Category is required")
    private Long categoryId;

    @NotBlank(message = "Product name is required")
    private String name;

    @Size(max=500)
    private String description;

//    @NotNull(message = "Warehouse is required")
//    private Long warehouseId;

//    @NotNull
    private String status;

    public ProductDTO() {
    }

    public ProductDTO(Long categoryId, String name, String description, String status) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public @NotNull(message = "Category is required") Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NotNull(message = "Category is required") Long categoryId) {
        this.categoryId = categoryId;
    }

    public @NotBlank(message = "Product name id required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Product name id required") String name) {
        this.name = name;
    }

    public @Size(max = 500) String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 500) String description) {
        this.description = description;
    }

//    public @NotNull String getStatus() {
//        return status;
//    }

//    public void setStatus(@NotNull String status) {
//        this.status = status;
//    }
}
