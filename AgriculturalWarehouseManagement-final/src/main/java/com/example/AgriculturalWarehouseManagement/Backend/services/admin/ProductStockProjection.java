package com.example.AgriculturalWarehouseManagement.Backend.services.admin;

public interface ProductStockProjection {
    Long getProductId();
    String getProductName();
    Long getTotalRemaining();
}
