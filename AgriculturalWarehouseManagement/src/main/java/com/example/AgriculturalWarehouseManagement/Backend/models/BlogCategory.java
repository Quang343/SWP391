//package com.example.AgriculturalWarehouseManagement.Backend.models;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name = "blogcategory")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Getter
//@Setter
//public class BlogCategory {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "BlogCategoryID")
//    private Long blogCategoryId;
//
//    @Column(name = "CategoryName", length = 100)
//    private String categoryName;
//
//    @Column(name = "Description", length = 500)
//    private String description;
//
//    @ManyToOne
//    @JoinColumn(name = "BlogCategoryID")
//    private BlogCategory blogCategory;
//
//}
