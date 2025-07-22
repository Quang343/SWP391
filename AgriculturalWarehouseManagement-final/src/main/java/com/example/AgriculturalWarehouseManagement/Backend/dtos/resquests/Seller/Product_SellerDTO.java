package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product_SellerDTO {

    private Long id;

    private String name;

    @Size(max = 200, message = "Description must be less than 200 characters!")
    private String description;

    private String categoryName;

    private String status;
}
