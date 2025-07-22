package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDTO {

    @NotBlank(message = "Voucher code is required")
    private String voucherCode;

    @NotNull(message = "Start date is required")
    private LocalDateTime startDate;

    @NotNull(message = "End date is required")
//    @Future(message = "End date must be in the future")
    private LocalDateTime endDate;

    @NotBlank(message = "Discount type is required")
    private String discountType;

    @NotNull(message = "Discount is required")
    private BigDecimal discountValue;

    @NotNull(message = "Quantity is required")
    private Long quantity;

//    @NotBlank(message = "Used quantity id required")
    private Long usedQuantity;

    private BigDecimal minOrderAmount;

}