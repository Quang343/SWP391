package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.Seller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSellerResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String categoryName;
    private String status;
}
