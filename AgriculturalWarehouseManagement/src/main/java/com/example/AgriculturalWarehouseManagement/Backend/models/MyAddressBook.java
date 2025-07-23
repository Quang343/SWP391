package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "myaddressbook")
public class MyAddressBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addressbookid")
    private int addressBookID;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userID", nullable = false)
    private User user;  // Quan hệ Many-to-One với bảng User

    @Column(name = "fullname", length = 255)
    private String fullName;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "note", length = 500)
    private String note;

    @Column(name = "settoaddress", length = 255)
    private String setToAddress;

    @Column(name = "createdat", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "email", length = 255)
    private String email;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = new Date();
        }
    }

    public int getAddressBookID() {
        return addressBookID;
    }

    public void setAddressBookID(int addressBookID) {
        this.addressBookID = addressBookID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getSetToAddress() {
        return setToAddress;
    }

    public void setSetToAddress(String setToAddress) {
        this.setToAddress = setToAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
