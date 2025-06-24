package com.example.AgriculturalWarehouseManagement.dtos.Seller;

import lombok.*;

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
