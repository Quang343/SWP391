package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.blog;
import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Getter
@Setter
public class BlogDTO {
    private Integer blogID;
    private String title;
    private String content;
    private String status;
    private String author;
    private String createdAt;
    private String thumbnail; // lấy từ blogDetail (nếu có)
    // ...getter/setter...
}
