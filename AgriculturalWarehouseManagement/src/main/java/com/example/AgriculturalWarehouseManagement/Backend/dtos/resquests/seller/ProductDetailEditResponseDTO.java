package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailEditResponseDTO {
    private Integer id;
    private String productName;
    private double weight;
    private String unit;
    private double price;
    private int expiry;
    private String status;
}
