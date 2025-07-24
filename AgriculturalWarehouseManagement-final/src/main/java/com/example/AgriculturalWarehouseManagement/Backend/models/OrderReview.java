package com.example.AgriculturalWarehouseManagement.Backend.models;

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
@Table(name = "`orderreview`")
public class OrderReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderreviewid")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @Column(name = "rating", columnDefinition = "INT DEFAULT 0")
    private int rating = 0;

    @Column(name = "image", length = 500)
    private String image;

    @Column(name = "message", length = 500)
    private String message;

    @Column(name = "updatedatetime", columnDefinition = "DATETIME")
    private LocalDateTime updateDateTime;

    @Column(name = "ordercode", nullable = false, unique = true)
    private String orderCode;

    @Column(name = "updatetimeimage", nullable = false)
    private String updateTimeImage;

    @Column(name = "updatetimeform", nullable = false)
    private String updateTimeForm;



}
