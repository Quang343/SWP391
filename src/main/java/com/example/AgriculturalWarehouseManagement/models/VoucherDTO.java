package com.example.AgriculturalWarehouseManagement.models;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDTO {

    @NotBlank(message = "Voucher code is required")
    private String code;

    @NotNull(message = "Discount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Discount must be greater than 0")
    @DecimalMax(value = "100.0", message = "Discount must be less than or equal to 100")
    private BigDecimal discount;

    @NotNull(message = "Order ID is required")
    private Long orderId;
}