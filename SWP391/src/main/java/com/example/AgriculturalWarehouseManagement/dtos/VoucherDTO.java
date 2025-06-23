package com.example.AgriculturalWarehouseManagement.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

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