package com.example.AgriculturalWarehouseManagement.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Voucher")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "voucher_code", nullable = false, length = 50)
    private String voucherCode;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "discount_type", nullable = false)
    private String discountType;

    @Column(name = "discount_value", precision = 15, scale = 2, nullable = false)
    private BigDecimal discountValue;

    private Long quantity;

    @Column(name = "used_quantity")
    private Long usedQuantity;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private VoucherStatus status;
}
