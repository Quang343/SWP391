package com.example.AgriculturalWarehouseManagement.Backend.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "warehouse")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouseid")
    private Long id;

    @Column(name = "warehousename", length = 255, nullable = false)
    private String warehouseName;

    @Column(name = "location", length = 255)
    private String location;

    @Column
    private String description;

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY)
    @JsonManagedReference("warehouse-products")
    private List<Product> products;
}