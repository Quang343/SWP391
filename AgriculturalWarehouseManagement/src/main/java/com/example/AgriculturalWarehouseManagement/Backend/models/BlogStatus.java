package com.example.AgriculturalWarehouseManagement.Backend.models;

//@author: Đào Huy Hoàng

public enum BlogStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive"), // Admin xóa mềm
    DRAFT("Draft"),
    PENDING("Pending"),
    PUBLISHED("Published"),
    DELETED("Deleted");

    private final String status;

    BlogStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}
