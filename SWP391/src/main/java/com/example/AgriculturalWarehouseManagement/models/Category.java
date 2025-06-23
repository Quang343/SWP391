package com.example.AgriculturalWarehouseManagement.models;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryid")
    private Long id;

    @Column(name = "categoryname", length = 100, nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(length = 100)
    private String status;

    @Column(name = "image_url", length = 100)
    private String imageUrl;
}
