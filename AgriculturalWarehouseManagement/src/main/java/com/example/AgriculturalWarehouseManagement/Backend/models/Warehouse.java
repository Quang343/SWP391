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
@Table(name = "warehouse")

public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouseid")
    private Integer id;

    @Column(name = "warehousename", length = 255, nullable = false)
    private String warehouseName;

    @Column(name = "location", length = 255)
    private String location;

    @Column
    private String description;

}