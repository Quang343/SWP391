package com.example.AgriculturalWarehouseManagement.models;

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
    private int id;

    @ManyToOne
    @JoinColumn(name = "productid", nullable = false)
    private Product product;

    @Column(name = "price")
    private double price;

    @Column(name="weight")
    private double weight;

    @Column(name="expiry")
    private int expiry;

    @Column(name="detailname", length = 500)
    private String detailname;

}
