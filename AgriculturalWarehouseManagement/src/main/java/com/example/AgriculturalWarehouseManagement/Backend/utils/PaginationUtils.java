package com.example.AgriculturalWarehouseManagement.Backend.utils;


import java.util.ArrayList;
import java.util.List;

public class PaginationUtils {
    public static List<String> generatePageNumbers(int totalPages, int currentPage) {
        List<String> pageNumbers = new ArrayList<>();

        if (totalPages <= 7) {
            // Hiển thị tất cả nếu tổng số trang nhỏ hơn hoặc bằng 7
            for (int i = 1; i <= totalPages; i++) {
                pageNumbers.add(String.valueOf(i));
            }
        } else {
            if (currentPage <= 3) {
                // Trang đầu: hiển thị 1 2 3 4 5 ... totalPages
                for (int i = 1; i <= currentPage + 1; i++) {
                    pageNumbers.add(String.valueOf(i));
                }
                pageNumbers.add("...");
                pageNumbers.add(String.valueOf(totalPages));
            } else if (currentPage >= totalPages - 2) {
                // Trang cuối: hiển thị 1 ... totalPages-4 → totalPages
                pageNumbers.addAll(List.of("1", "2"));
                pageNumbers.add("...");
                for (int i = totalPages - 3; i <= totalPages; i++) {
                    pageNumbers.add(String.valueOf(i));
                }
            } else{
                // Ở giữa: hiển thị 1 ... currentPage-1 currentPage currentPage+1 ... totalPages
                pageNumbers.addAll(List.of("1", "..."));
                for (int i = currentPage - 1; i <= currentPage + 1; i++) {
                    pageNumbers.add(String.valueOf(i));
                }
                pageNumbers.add("...");
                pageNumbers.add(String.valueOf(totalPages));
            }
        }

        return pageNumbers;
    }
}
