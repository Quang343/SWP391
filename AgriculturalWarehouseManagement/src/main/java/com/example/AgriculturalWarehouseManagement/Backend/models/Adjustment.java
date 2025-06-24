package com.example.AgriculturalWarehouseManagement.Backend.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Adjustment")
public class Adjustment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adjustmentid")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "batchid")
    private ProductBatch batch;

    @Column(name = "adjustmentquantity", nullable = false)
    private Integer quantity;

    @Column(name = "adjustdate")
    private LocalDateTime adjustDate;

    @Column(name = "reason")
    private String reason;

    @Column(name = "adjustmenttype", nullable = false)
    @Enumerated(EnumType.STRING)
    private AdjustmentType adjustmentType;


}

