package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartid")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userID", nullable = false)
    private User user;  // Quan hệ Many-to-One với bảng User

    @ManyToOne
    @JoinColumn(name = "productdetailid", nullable = false)
    private ProductDetail productDetail;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total")
    private double total;

    @Column(name = "createdat")
    private LocalDateTime createdAt;
}