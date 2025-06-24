package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDTO {

    @NotNull
    private Long productId;

    private String imageUrl;
}
