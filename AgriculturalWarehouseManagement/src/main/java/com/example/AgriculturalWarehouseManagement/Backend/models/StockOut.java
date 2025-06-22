package com.example.AgriculturalWarehouseManagement.Backend.models;

import com.example.AgriculturalWarehouseManagement.Backend.models.Order;
import com.example.AgriculturalWarehouseManagement.Backend.models.Warehouse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "stockout")
public class StockOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stockoutid", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "warehouseid", nullable = false)
    private Warehouse warehouseID;

    @ManyToOne
    @JoinColumn(name = "orderid")
    private Order orderID;


    @Column(name = "stockoutdate")
    private LocalDate stockOutDate;

    @Lob
    @Column(name = "note")
    private String note;

}