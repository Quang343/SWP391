package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private int userID;

    @ManyToOne
    @JoinColumn(name = "roleID", referencedColumnName = "RoleID", nullable = false)
    private Role role;  // Quan hệ Many-to-One với bảng Role

    @Column(name = "username", length = 100)
    private String userName;

    @Column(name = "fullname", length = 255)
    private String fullName;

    @Column(name = "image", length = 255)
    private String image;

    @Column(name = "passwordHash", length = 255)
    private String passwordHash;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "CreatedAt", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "otp", length = 10)
    private String otp;

    @Column(name = "lastTimeUpdatePass")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastTimeUpdatePass;

    @Column(name = "googleID", length = 255)
    private String googleID;

    @Column(name = "statusgg", length = 255)
    private String statusGG;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = new Date();
        }
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Date getLastTimeUpdatePass() {
        return lastTimeUpdatePass;
    }

    public void setLastTimeUpdatePass(Date lastTimeUpdatePass) {
        this.lastTimeUpdatePass = lastTimeUpdatePass;
    }

    public String getGoogleID() {
        return googleID;
    }

    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    public String getStatusGG() {
        return statusGG;
    }

    public void setStatusGG(String statusGG) {
        this.statusGG = statusGG;
    }
}
