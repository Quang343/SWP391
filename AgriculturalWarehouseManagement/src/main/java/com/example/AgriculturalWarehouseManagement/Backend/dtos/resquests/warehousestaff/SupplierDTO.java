package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff;

public class SupplierDTO {

    private Integer supplierID;
    private String supplierName;
    private String contactInfo;
    private String logo;

    public SupplierDTO() {
    }

    public SupplierDTO(Integer supplierID, String supplierName, String contactInfo, String logo) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.contactInfo = contactInfo;
        this.logo = logo;
    }


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

    public String getLogo() {return logo;}

    public void setLogo(String logo) {this.logo = logo;}
}