package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "seller_application")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // liên kết với bảng user (seller)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // số tháng hợp đồng (int)
    @Column(name = "contract_months", nullable = false)
    private Integer contractMonths;

    // link ảnh CV đã upload (có thể là đường dẫn tương đối)
    @Column(name = "cv_image", nullable = false)
    private String cvImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SellerApplicationStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "contract_expiry_date")
    private LocalDate contractExpiryDate;
}

