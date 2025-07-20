package com.example.AgriculturalWarehouseManagement.Backend.utils;

import lombok.Builder;

@Builder
public record ChangePassword(String password, String rePassword) {
}
