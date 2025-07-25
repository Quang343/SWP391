package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ViewCommentProductUserResponse {
    private int commentProductId;
    private int productId;
    private int userId;
    private String userName;
    private String imageUrl;
    private LocalDateTime createdAt;
    private int rating;
    private String commentText;
    private int dislikes;
    private int likes;
    private String status;

    public ViewCommentProductUserResponse() {
    }

    // Constructors
    public ViewCommentProductUserResponse(int commentProductId, int productId, int userId, String userName,String imageUrl,
                                          LocalDateTime createdAt, int rating, String commentText,
                                          int dislikes, int likes, String status) {
        this.commentProductId = commentProductId;
        this.productId = productId;
        this.userId = userId;
        this.userName = userName;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.rating = rating;
        this.commentText = commentText;
        this.dislikes = dislikes;
        this.likes = likes;
        this.status = status;
    }

    public String formatCreateAt() {
        if (createdAt != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return createdAt.format(formatter);  // Format without the "T"
        }
        return null;  // Return null if createAt is null
    }

    // Getters and Setters
    public int getCommentProductId() {
        return commentProductId;
    }

    public void setCommentProductId(int commentProductId) {
        this.commentProductId = commentProductId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ViewCommentProductUserResponse{" +
                "commentProductId=" + commentProductId +
                ", productId=" + productId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", createdAt=" + createdAt +
                ", rating=" + rating +
                ", commentText='" + commentText + '\'' +
                ", dislikes=" + dislikes +
                ", likes=" + likes +
                ", status='" + status + '\'' +
                '}';
    }
}
