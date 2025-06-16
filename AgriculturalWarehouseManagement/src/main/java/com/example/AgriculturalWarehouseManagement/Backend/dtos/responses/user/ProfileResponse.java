package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ProfileResponse {

    private int userID;
    private String userName;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String gender;
    private String dob;
    private Date lastUpdateAt;

    public ProfileResponse() {
    }

    public ProfileResponse(int userID, String userName, String fullName, String email, String phone, String address, String gender, String dob, Date lastUpdateAt) {
        this.userID = userID;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.dob = dob;
        this.lastUpdateAt = lastUpdateAt;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Date getLastUpdateAt() {
        return lastUpdateAt;
    }

    public void setLastUpdateAt(Date lastUpdateAt) {
        this.lastUpdateAt = lastUpdateAt;
    }

    @Override
    public String toString() {
        return "ProfileRequest{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                ", lastUpdateAt=" + lastUpdateAt +
                '}';
    }
}
