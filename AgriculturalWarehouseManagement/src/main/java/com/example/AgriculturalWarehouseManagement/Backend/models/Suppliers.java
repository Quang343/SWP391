package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;

@Entity
public class Suppliers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer supplierID;

    @Column(name = "suppliername", nullable = false, length = 255)
    private String supplierName;

    @Column(name = "contactinfo", length = 500)
    private String contactInfo;

    // Getters and Setters

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}