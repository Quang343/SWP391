package com.example.AgriculturalWarehouseManagement.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    @NotNull
    private Long orderId;

    @NotNull
    @Min(1)
    private Long productDetailId;

//    @NotNull
//    private Long batchId;

    @NotNull
    private int quantity;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;
}
