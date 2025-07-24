package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

//@author: Đào Huy Hoàng

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "commentblog")
public class CommentBlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentblogid")
    private int commentBlogId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", referencedColumnName = "userID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blogid", referencedColumnName = "blogid", nullable = false)
    private Blog blog;

    @Column(name = "commenttext", columnDefinition = "TEXT")
    private String commentText;

    @Column(name = "likes", columnDefinition = "INT DEFAULT 0")
    private int likes = 0;

    @Column(name = "dislikes", columnDefinition = "INT DEFAULT 0")
    private int dislikes = 0;

    @Column(name = "status", length = 50)
    private String status = "Active";

    @Column(name = "createdat", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}

//CREATE TABLE commentblog (
//        commentblogid INT PRIMARY KEY AUTO_INCREMENT,
//        userid INT NOT NULL,
//        blogid INT NOT NULL,
//        commenttext TEXT,
//        likes INT DEFAULT 0,
//        dislikes INT DEFAULT 0,
//        status VARCHAR(50) DEFAULT 'Active',
//createdat DATETIME DEFAULT CURRENT_TIMESTAMP,
//FOREIGN KEY (userid) REFERENCES user(userID),
//FOREIGN KEY (blogid) REFERENCES blog(blogid)
//        );
