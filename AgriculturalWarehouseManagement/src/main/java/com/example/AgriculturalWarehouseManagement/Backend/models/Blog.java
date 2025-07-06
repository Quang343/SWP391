package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blogid")
    private Integer blogID;

    @Column(name = "userid")
    private Integer userID;

    @Column(name = "blogcategoryid")
    private Integer blogCategoryID;

    @NotBlank(message = "Tiêu đề không được để trống")
    @Size(max = 255, message = "Tiêu đề không được vượt quá 255 ký tự")
    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "createdat")
    private Date createdAt;

    @Column(name = "blogdateupdate")
    private Date blogDateUpdate;


    @Enumerated(EnumType.STRING) // Sử dụng EnumType.STRING để lưu tên trạng thái dưới dạng chuỗi
    @Column(name = "status")
    private BlogStatus status;

    @Column(name = "author")
    private String author;

    // Liên kết 1-1 với BlogDetail (mappedBy phía BlogDetail)
    @OneToOne(mappedBy = "blog", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private BlogDetail blogDetail;

    // Thêm sau
//    @ManyToOne
//    @JoinColumn(name = "userid")
//    private User user;


}

