package com.example.AgriculturalWarehouseManagement.Backend.models;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

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

    @Column(name = "passwordhash", length = 255)
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

    @Column(name = "createdat", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "otp", length = 10)
    private String otp;

    @Column(name = "lasttimeupdatepass")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastTimeUpdatePass;

    @Column(name = "googleid", length = 255)
    private String googleID;

    @Column(name = "statusgg", length = 255)
    private String statusGG;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MyAddressBook> myAddressBooks;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<UpdateProfileHistory> updateProfileHistories;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<ContactUsUser> contactUsUser;

    // Getter and Setter methods
    public List<MyAddressBook> getMyAddressBooks() {
        return myAddressBooks;
    }

    public void setMyAddressBooks(List<MyAddressBook> myAddressBooks) {
        this.myAddressBooks = myAddressBooks;
    }

//    public List<UpdateProfileHistory> getUpdateProfileHistories() {
//        return updateProfileHistories;
//    }
//
//    public void setUpdateProfileHistories(List<UpdateProfileHistory> updateProfileHistories) {
//        this.updateProfileHistories = updateProfileHistories;
//    }
//
//    public List<ContactUsUser> getContactUsUser() {
//        return contactUsUser;
//    }
//
//    public void setContactUsUser(List<ContactUsUser> contactUsUser) {
//        this.contactUsUser = contactUsUser;
//    }

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
