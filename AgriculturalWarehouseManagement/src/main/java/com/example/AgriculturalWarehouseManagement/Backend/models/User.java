package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private int userId;

    @ManyToOne()
    @JoinColumn(name = "roleid")
    private Role role;

    @Column(name = "username", length = 100)
    private String userName;

    @Column(name = "fullname", length = 255)
    private String fullName;

    @Column(name = "image", length = 255)
    private String image;

    @Column(length = 255)
    private String password;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(length = 255)
    private String address;

    @Column(length = 10)
    private String gender;

    @Column(length = 20)
    private String status; // e.g. Active, Inactive

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(length = 10)
    private String otp;

    @Column(name = "lasttimeupdatepass")
    private LocalDateTime lastTimeUpdatePass;

    @Column(name = "googleid", length = 255)
    private String googleID;

    @Column(name = "statusgg", length = 255)
    private String statusGG;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.lastTimeUpdatePass = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MyAddressBook> myAddressBooks;

}