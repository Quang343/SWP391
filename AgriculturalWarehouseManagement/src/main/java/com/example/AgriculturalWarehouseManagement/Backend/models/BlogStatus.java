package com.example.AgriculturalWarehouseManagement.Backend.models;

//@author: Đào Huy Hoàng

public enum BlogStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"), // Admin xóa mềm
    DRAFT("DRAFT"),
 //   PENDING("PENDING"),
   // PUBLISHED("PUBLISHED"),
    DELETED("DELETED");

    private final String status;

    BlogStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}
