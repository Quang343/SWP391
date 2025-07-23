package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderReviewUserResponse {
    private int orderReviewUserId;
    private String image;
    private String message;
    private int rating;
    private String orderCode;
    private LocalDateTime updateDateTime;
    private LocalDateTime orderDate;
    private String updateTimeFormatted;
    private String updatedTimeImage;
    private int userId;
    private String userName;
    private String userEmail;

    public OrderReviewUserResponse() {
    }

    public OrderReviewUserResponse(int orderReviewUserId, String image, String message, int rating, String orderCode, LocalDateTime updateDateTime, LocalDateTime orderDate, String updateTimeFormatted, String updatedTimeImage, int userId, String userName, String userEmail) {
        this.orderReviewUserId = orderReviewUserId;
        this.image = image;
        this.message = message;
        this.rating = rating;
        this.orderCode = orderCode;
        this.updateDateTime = updateDateTime;
        this.orderDate = orderDate;
        this.updateTimeFormatted = updateTimeFormatted;
        this.updatedTimeImage = updatedTimeImage;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public String formatUpdateDateTime() {
        if (updateDateTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return updateDateTime.format(formatter);  // Format without the "T"
        }
        return "";  // Return null if createAt is null
    }


    public int getOrderReviewUserId() {
        return orderReviewUserId;
    }

    public void setOrderReviewUserId(int orderReviewUserId) {
        this.orderReviewUserId = orderReviewUserId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getUpdateTimeFormatted() {
        return updateTimeFormatted;
    }

    public void setUpdateTimeFormatted(String updateTimeFormatted) {
        this.updateTimeFormatted = updateTimeFormatted;
    }

    public String getUpdatedTimeImage() {
        return updatedTimeImage;
    }

    public void setUpdatedTimeImage(String updatedTimeImage) {
        this.updatedTimeImage = updatedTimeImage;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "OrderReviewUserResponse{" +
                "orderReviewUserId=" + orderReviewUserId +
                ", image='" + image + '\'' +
                ", message='" + message + '\'' +
                ", rating=" + rating +
                ", orderCode='" + orderCode + '\'' +
                ", updateDateTime=" + updateDateTime +
                ", orderDate=" + orderDate +
                ", updateTimeFormatted='" + updateTimeFormatted + '\'' +
                ", updatedTimeImage='" + updatedTimeImage + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
