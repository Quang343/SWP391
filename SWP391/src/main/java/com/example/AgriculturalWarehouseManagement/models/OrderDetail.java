package com.example.AgriculturalWarehouseManagement.models;

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

//    @Column(name = "batchid", nullable = false)
//    private Long batchId;

    @Column(nullable = false)
    private int quantity;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal price;
}
