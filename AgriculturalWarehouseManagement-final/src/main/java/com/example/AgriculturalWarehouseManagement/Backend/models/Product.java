package com.example.AgriculturalWarehouseManagement.Backend.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "categoryid", nullable = false)
    private Category category;

    @Column(name = "productname", length = 255, nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    //    thanh
    @OneToMany(mappedBy = "productID", fetch = FetchType.LAZY)
    private List<ProductDetail> productDetails;
    //    thanh
}
