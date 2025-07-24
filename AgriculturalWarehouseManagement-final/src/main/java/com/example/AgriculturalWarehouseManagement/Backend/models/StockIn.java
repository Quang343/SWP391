package com.example.AgriculturalWarehouseManagement.Backend.models;

import com.example.AgriculturalWarehouseManagement.Backend.models.Suppliers;
import com.example.AgriculturalWarehouseManagement.Backend.models.Warehouse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "stockin")
public class StockIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Stockinid", nullable = false)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "supplierid", nullable = false)
    private Suppliers supplierID;


    @ManyToOne
    @JoinColumn(name = "warehouseid", nullable = false)
    private Warehouse warehouseID;

    @Column(name = "stockindate")
    private LocalDateTime stockInDate;

    @Lob
    @Column(name = "note")
    private String note;

}