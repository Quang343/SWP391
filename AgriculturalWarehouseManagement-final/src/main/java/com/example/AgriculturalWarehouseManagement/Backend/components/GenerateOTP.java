package com.example.AgriculturalWarehouseManagement.Backend.components;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GenerateOTP {
    public String generateOTP() {
        // Tạo một chuỗi gồm tất cả các chữ cái (hoa và thường) và các chữ số ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxy
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(characters.length());
            code.append(characters.charAt(randomIndex));
        }

        return code.toString();
    }
}
