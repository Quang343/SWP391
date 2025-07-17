package com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.bloger ;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminBlogDTO {
    private Integer blogID;
    private String title;
    private String content;
    private Integer blogCategoryID;  // <-- Sửa từ Long về Integer cho khớp user DTO và entity luôn
    private String status;
    private String author;
    private String createdAt;
    private String thumbnail;
    private String blogCategoryName; // Thêm cho admin
   // private Long blogCategoryID;  // Thêm cho admin nếu muốn
}
