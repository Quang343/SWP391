package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin;

public interface BestSellerProductDTO {
    String getImageUrl();
    String getProductName();
    Double getPrice();
    Long getTotalSold();
    Long getTotalInStock();
}
