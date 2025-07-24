package com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ContactUsUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import jakarta.persistence.Column;

public class ContactUsUserRequests {

    private String fullName;

    private String phone;

    private String address;

    private String message;

    private String email;

    public ContactUsUserRequests() {
    }

    public ContactUsUserRequests(String fullName, String phone, String address, String message, String email) {
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.message = message;
        this.email = email;
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
        return "ContactUsUserRequests{" +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", message='" + message + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
