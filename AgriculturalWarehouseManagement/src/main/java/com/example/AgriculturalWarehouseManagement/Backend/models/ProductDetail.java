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
@Table(name = "productdetail")
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productdetailid")
    private Integer productDetailId;

    @ManyToOne
    @JoinColumn(name = "productid", nullable = false)
    private Product productID;

    @Column(name = "price")
    private double price;

    @Column(name="weight")
    private double weight;

    @Column(name="expiry")
    private Integer expiry;

    @Column(name="detailname", length = 500)
    private String detailName;

}
