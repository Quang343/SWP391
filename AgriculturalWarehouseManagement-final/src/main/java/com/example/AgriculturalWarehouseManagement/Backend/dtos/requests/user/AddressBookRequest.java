package com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user;

public class AddressBookRequest {

    private int userId;
    private String fullName;
    private String phone;
    private String address;
    private String note;
    private String setToAddress;
    private String email;

    public AddressBookRequest() {
    }

    public AddressBookRequest(int userId, String fullName, String phone, String address, String note, String setToAddress, String email) {
        this.userId = userId;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.setToAddress = setToAddress;
        this.email = email;
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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AddressBookRequest{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", note='" + note + '\'' +
                ", setToAddress='" + setToAddress + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
