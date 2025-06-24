package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.Seller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail_SellerDTO {
    @NotNull(message = "Product ID không được để trống")
    private Long productId;

    @NotNull(message = "Weight không được để trống")
    @Positive(message = "Weight phải > 0")
    private Double weight;

    @NotBlank(message = "Unit không được trống")
    private String unit;

    @NotNull(message = "Price không được để trống")
    @PositiveOrZero
    private Double price;

    @NotNull(message = "Expiry không được để trống")
    @Positive
    private Integer expiry;

    @NotBlank(message = "Status không được để trống")
    private String status;
}