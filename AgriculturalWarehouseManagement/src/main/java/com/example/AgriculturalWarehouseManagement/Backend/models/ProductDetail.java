package com.example.AgriculturalWarehouseManagement.Backend.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "productdetail")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "productDetailId")
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productdetailid", nullable = false)
    private Integer productDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid")
    @JsonIdentityReference(alwaysAsId = true) // Serialize only the ID of Product
    private Product productID;

    @Column(name = "price", precision = 15, scale = 2)
    private BigDecimal price;

    @Column(name = "weight", precision = 10, scale = 2)
    private BigDecimal weight;

    @Column(name = "expiry")
    private Integer expiry;

    @Size(max = 255)
    @Column(name = "detailname")
    private String detailName;

    @OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ProductBatch> productBatch;

    public ProductDetail() {
    }

    public ProductDetail(Integer productDetailId, Product productID, BigDecimal price, BigDecimal weight, Integer expiry, String detailName, List<ProductBatch> productBatch) {
        this.productDetailId = productDetailId;
        this.productID = productID;
        this.price = price;
        this.weight = weight;
        this.expiry = expiry;
        this.detailName = detailName;
        this.productBatch = productBatch;
    }

    public @Size(max = 255) String getDetailName() {
        return detailName;
    }

    public void setDetailName(@Size(max = 255) String detailName) {
        this.detailName = detailName;
    }
}