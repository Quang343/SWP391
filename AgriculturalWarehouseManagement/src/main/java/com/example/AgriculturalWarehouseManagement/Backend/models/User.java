package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userId;

    @ManyToOne()
    @JoinColumn(name = "roleid")
    private Role role;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "fullname", length = 255)
    private String fullName;

    @Column(length = 255)
    private String image;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 100)
    private String email;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(length = 255)
    private String address;

    @Column(length = 10)
    private String gender;

    @Column(length = 20, nullable = false)
    private String status; // e.g., Ban, Active, Inactive

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(length = 10)
    private String otp;

    @Column(name = "lasttimeupdatepass")
    private LocalDateTime lastTimeUpdatePass;

//    @OneToOne(mappedBy = "user")
//    private ForgotPassword forgotPassword;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.lastTimeUpdatePass = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastTimeUpdatePass = LocalDateTime.now();
    }

}
