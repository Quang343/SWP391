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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryid", nullable = false)
    @JsonIdentityReference(alwaysAsId = true) // Chỉ hiển thị ID của Category
    private Category category;

    @Column(name = "productname", length = 255, nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouseid", nullable = false)
    @JsonIdentityReference(alwaysAsId = true) // Chỉ hiển thị ID của Warehouse
    private Warehouse warehouse;

    @OneToMany(mappedBy = "productID", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("product-productdetails")
    private List<ProductDetail> productDetails;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
}