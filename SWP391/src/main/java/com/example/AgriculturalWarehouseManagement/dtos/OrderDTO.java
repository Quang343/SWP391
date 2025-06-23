package com.example.AgriculturalWarehouseManagement.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @NotNull
    @Min(value = 1, message = "User's ID must be > 0")
    private Long userId;

    private LocalDateTime orderDate;

    @NotNull
    private String status;

    @NotBlank
    private BigDecimal totalAmount;

    private String fullName;

    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String address;

    private String note;

    private Long voucherId;

}
