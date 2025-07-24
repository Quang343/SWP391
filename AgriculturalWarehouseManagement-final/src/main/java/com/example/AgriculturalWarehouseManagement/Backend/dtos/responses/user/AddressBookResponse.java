package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

import java.util.Date;

public class AddressBookResponse {

    private int addressBookId;
    private int userId;
    private String fullName;
    private String phone;
    private String address;
    private String note;
    private String setToAddress;
    private String email;
    private Date dateCreated;

    public AddressBookResponse() {
    }

    public AddressBookResponse(int addressBookId,int userId, String fullName, String phone, String address, String note, String setToAddress, String email, Date dateCreated) {
        this.addressBookId = addressBookId;
        this.userId = userId;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.setToAddress = setToAddress;
        this.email = email;
        this.dateCreated = dateCreated;
    }

    public int getAddressBookId() {
        return addressBookId;
    }

    public void setAddressBookId(int addressBookId) {
        this.addressBookId = addressBookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getSetToAddress() {
        return setToAddress;
    }

    public void setSetToAddress(String setToAddress) {
        this.setToAddress = setToAddress;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
