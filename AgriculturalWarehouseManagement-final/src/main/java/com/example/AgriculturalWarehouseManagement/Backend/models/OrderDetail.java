package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`orderdetail`")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderdetailid")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orderid", nullable = false)
    private Order order;

    @Column(name = "productdetailid", nullable = false)
    private Long productDetailId;

//    @ManyToOne
//    @JoinColumn(name = "productdetailid", nullable = false)
//    private ProductDetail productDetail;

    @Column(nullable = false)
    private int quantity;

    @Column(precision = 15, scale = 2)
    private BigDecimal price;


}
