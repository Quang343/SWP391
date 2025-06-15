package com.example.AgriculturalWarehouseManagement.Backend.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "productbatch")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "batchId")
public class ProductBatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batchid", nullable = false)
    private Integer batchId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productdetailid")
    @JsonIdentityReference(alwaysAsId = true) // Chỉ hiển thị ID của ProductDetail
    private ProductDetail productDetail;

    @Column(name = "manufacturedate")
    private LocalDate manufactureDate;

    @Column(name = "importedquantity")
    private Integer importedQuantity;

    @Column(name = "soldquantity")
    private Integer soldQuantity;


}