package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

public class ContactUsUserResponse {

    private int contactUsUserId;
    private int userId;
    private String fullName;
    private String phone;
    private String address;
    private String message;
    private String email;

    public ContactUsUserResponse() {
    }

    public ContactUsUserResponse(int contactUsUserId, int userId, String phone, String fullName, String address, String email, String message) {
        this.contactUsUserId = contactUsUserId;
        this.userId = userId;
        this.phone = phone;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.message = message;
    }

    public int getContactUsUserId() {
        return contactUsUserId;
    }

    public void setContactUsUserId(int contactUsUserId) {
        this.contactUsUserId = contactUsUserId;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ContactUsUserResponse{" +
                "contactUsUserId=" + contactUsUserId +
                ", userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", message='" + message + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
