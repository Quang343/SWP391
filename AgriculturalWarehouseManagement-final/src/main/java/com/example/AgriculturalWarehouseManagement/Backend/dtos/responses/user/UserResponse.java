package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

import com.example.AgriculturalWarehouseManagement.Backend.models.Role;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UserResponse {
    private int userID;
    private Role role;
    private String userName;
    private String fullName;
    private String imageUrl;
    private String email;
    private String phone;
    private String address;
    private String gender;
    private String status;
    private String dob;
    private LocalDateTime createAt;
    private LocalDateTime lastUpdateAt;
    private String googleID;
    private String statusGG;

    public UserResponse() {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public String formatCreateAt() {
        if (createAt != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return createAt.format(formatter);  // Format without the "T"
        }
        return null;  // Return null if createAt is null
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getLastUpdateAt() {
        return lastUpdateAt;
    }

    public void setLastUpdateAt(LocalDateTime lastUpdateAt) {
        this.lastUpdateAt = lastUpdateAt;
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

    @Override
    public String toString() {
        return "UserResponse{" +
                "userID=" + userID +
                ", role=" + role +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                ", dob='" + dob + '\'' +
                ", createAt=" + createAt +
                ", lastUpdateAt=" + lastUpdateAt +
                ", googleID='" + googleID + '\'' +
                ", statusGG='" + statusGG + '\'' +
                '}';
    }
}
