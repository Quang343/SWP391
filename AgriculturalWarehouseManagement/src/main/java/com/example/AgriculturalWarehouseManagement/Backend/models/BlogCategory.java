package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;
import lombok.*;


//@author: Đào Huy Hoàng

@Entity
@Table(name = "blogcategory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BlogCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blogcategoryid") // ✅ sửa tên cột ở đây
    private Long blogCategoryId;

    @Column(name = "categoryname")
    private String categoryName;

    @Column(name = "description", length = 500)
    private String description;

    //    @ManyToOne
//    @JoinColumn(name = "BlogCategoryID")
//    private BlogCategory blogCategory;
// Nếu muốn: List các  thuộc category này (1 category - nhiều )
//    @OneToMany(mappedBy = "blogCategory")
//    private java.util.List<Blog> blogs;
}
