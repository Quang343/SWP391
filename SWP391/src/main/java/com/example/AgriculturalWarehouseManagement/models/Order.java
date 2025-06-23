package com.example.AgriculturalWarehouseManagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    private Long id;

    @Column(name = "ordercode", nullable = false, unique = true)
    private String orderCode;

    @ManyToOne()
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @Column(name = "orderdate", columnDefinition = "DATETIME")
    private LocalDateTime orderDate;

    @Column(length = 50, nullable = false)
    private String status;

    @Column(name = "fullname", length = 255)
    private String fullName;

    @Column(length = 100)
    private String email;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(length = 255, nullable = false)
    private String address;

    private String note;

    @Column(name = "totalamount", precision = 15, scale = 2, nullable = false)
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    @Column(name = "voucher_code", length = 50)
    private String voucherCode;

    @Column(name = "discount_amount", precision = 15, scale = 2)
    private BigDecimal discountAmount;

    @Column(name = "final_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal finalAmount;

    public String getStatusBadgeClass() {
        switch (status) {
            case "PENDING":
                return "pending";
            case "CONFIRMED":
                return "primary";
            case "DELIVERED":
                return "success";
            case "CANCELLED":
                return "danger";
            default:
                return "badge bg-secondary";
        }
    }
}
