package com.example.AgriculturalWarehouseManagement.Backend.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stockoutdetail")
public class StockOutDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stockoutdetailid", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "stockoutid", nullable = false)
    private StockOut stockOutID;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "orderdetailid", nullable = false)
    private OrderDetail orderDetailID;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "batchid", nullable = false)
    private ProductBatch batchID;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

}