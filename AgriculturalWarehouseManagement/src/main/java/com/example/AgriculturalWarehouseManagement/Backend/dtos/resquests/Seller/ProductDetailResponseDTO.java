package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.Seller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponseDTO {
    private Integer id;
    private String productName;
    private String detailName;
    private double price;
    private int expiry;
    private String status;
}
