package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`wallets`")
public class Wallets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "walletid")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @Column(name = "balance", precision = 20, scale = 2)
    private BigDecimal balance;
}
