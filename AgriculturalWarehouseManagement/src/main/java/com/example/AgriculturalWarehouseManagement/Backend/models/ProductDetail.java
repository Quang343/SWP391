package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;


@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "productdetail")
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productdetailid", nullable = false)
    private Integer productDetailId;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Product productID;

    @Column(name = "price", precision = 15, scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "categoryweightid")
    private CategoryWeight categoryWeight;

    @Column(name = "expiry")
    private Integer expiry;

    @Size(max = 255)
    @Column(name = "detailname")
    private String detailName;

}