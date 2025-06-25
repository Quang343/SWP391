package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "commentproduct")
public class CommentProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentproductid")
    private int commentProductId;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "productid", nullable = false)
    private Product product;

    @Column(name = "parentcommentid")
    private int parentComment;

    @Column(name = "commenttext", columnDefinition = "TEXT")
    private String commentText;

    @Column(name = "likes", columnDefinition = "INT DEFAULT 0")
    private int likes = 0;

    @Column(name = "report", columnDefinition = "INT DEFAULT 0")
    private int report = 0;

    @Column(name = "status", length = 50)
    private String status = "Active";

    @Column(name = "rating", columnDefinition = "INT DEFAULT 0")
    private int rating = 0;

    @Column(name = "createdat", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }
}

