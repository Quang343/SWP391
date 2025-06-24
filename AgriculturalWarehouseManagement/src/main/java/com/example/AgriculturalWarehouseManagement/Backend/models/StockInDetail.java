package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "stockindetail")
public class StockInDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stockindetailid", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "stockinid", nullable = false)
    private StockIn stockInID;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unitprice")
    private Integer unitPrice;

    @ManyToOne
    @JoinColumn(name = "batchid")
    private ProductBatch batchID;

}