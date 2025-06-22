package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@Entity
@Builder
@Table(name = "categoryweight")
@NoArgsConstructor
@AllArgsConstructor
public class CategoryWeight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryweightid", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "categoryid", nullable = false)
    private Category categoryID;

    @Column(name = "weight", precision = 10, scale = 2)
    private Long weight;

    @Size(max = 20)
    @Column(name = "unit", length = 20)
    private String unit;

}