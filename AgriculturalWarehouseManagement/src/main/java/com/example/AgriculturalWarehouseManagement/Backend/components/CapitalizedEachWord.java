package com.example.AgriculturalWarehouseManagement.Backend.components;

public class CapitalizedEachWord {
    public static boolean isCapitalizedEachWord(String str) {
        if (str == null || str.trim().isEmpty()) return false;

        String[] words = str.trim().split("\\s+");

        for (String word : words) {
            if (word.length() == 0) continue;
            char firstChar = word.charAt(0);
            if (!Character.isUpperCase(firstChar)) {
                return false;
            }
        }

        return true;
    }
}
