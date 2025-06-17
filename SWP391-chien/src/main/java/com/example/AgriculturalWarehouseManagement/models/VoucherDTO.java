package com.example.AgriculturalWarehouseManagement.models;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDTO {

    @NotBlank(message = "Voucher code is required")
    private String voucherCode;

    @NotNull(message = "Discount is required")
    private BigDecimal discountValue;

    @NotNull(message = "Start date is required")
    private Date startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private Date endDate;

    @NotBlank(message = "Discount type is required")
    @Pattern(regexp = "PERCENT|AMOUNT", message = "Discount type must be either 'PERCENT' or 'AMOUNT'")
    private String discountType;

    @NotNull(message = "Quantity is required")
    private Long quantity;

    @NotNull(message = "Status is required")
    private String status;
}