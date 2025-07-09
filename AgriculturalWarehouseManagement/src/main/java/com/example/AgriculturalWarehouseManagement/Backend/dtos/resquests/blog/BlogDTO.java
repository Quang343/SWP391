package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.blog;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Getter
@Setter
public class BlogDTO {
    private Integer blogID;
    private String title;
//    @Size(max = 20000, message = "Nội dung không được vượt quá 20000 ký tự")
    private String content;

    private String status;
    private String author;
    private String createdAt;
    private String thumbnail;
}
