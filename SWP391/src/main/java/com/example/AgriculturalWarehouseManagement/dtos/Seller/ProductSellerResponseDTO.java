package com.example.AgriculturalWarehouseManagement.dtos.Seller;

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
