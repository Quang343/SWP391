package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Gallery")
public class ProductImage {

    public static final int MAXIMUM_IMAGES_PER_PRODUCT = 5;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "galleryid")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "productid", nullable = false)
    private Product product;

    @Column(name = "imageurl")
    private String imageUrl;
}
