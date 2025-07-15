package com.example.AgriculturalWarehouseManagement.Backend.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productbatch")
public class ProductBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batchid", nullable = false)
    private Integer batchId;

    @ManyToOne
    @JoinColumn(name = "productdetailid", nullable = false)
    private ProductDetail productDetail;

    @Column(name = "manufacturedate")
    private LocalDate manufactureDate;

    @Column(name = "importedquantity")
    private Integer importedQuantity;

    @Column(name = "soldquantity")
    private Integer soldQuantity;
}
