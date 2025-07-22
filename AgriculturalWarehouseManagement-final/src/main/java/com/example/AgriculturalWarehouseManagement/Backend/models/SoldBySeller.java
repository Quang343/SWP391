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
@Table(name = "soldbyseller")
public class SoldBySeller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userID", nullable = false)
    private User user;  // Quan hệ Many-to-One với bảng User

    @ManyToOne
    @JoinColumn(name = "productdetailid", nullable = false)
    private ProductDetail productDetail;
}
