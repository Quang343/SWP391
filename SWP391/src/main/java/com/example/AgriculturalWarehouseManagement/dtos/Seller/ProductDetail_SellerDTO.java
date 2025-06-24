package com.example.AgriculturalWarehouseManagement.dtos.Seller;

import jakarta.validation.constraints.*;
import lombok.*;

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