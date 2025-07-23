package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin;

import lombok.Builder;

@Builder
public record MailBody(String to, String subject, String text) {
}
