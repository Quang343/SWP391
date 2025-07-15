package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Locale;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL: tạo AUTO_INCREMENT
    @Column(name = "CategoryID")
    private int categoryId;

    @Column(name = "categoryname", length = 100, nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(length = 100)
    private String status;

    @Column(name = "imageurl", length = 500)
    private String imageUrl;
}