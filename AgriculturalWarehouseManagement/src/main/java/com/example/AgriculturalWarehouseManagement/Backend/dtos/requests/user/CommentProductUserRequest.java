package com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user;

public class CommentProductUserRequest {
    private int productId;
    private int accountId;
    private int rating;
    private String content;

    public CommentProductUserRequest(int productId, int accountId, int rating, String content) {
        this.productId = productId;
        this.accountId = accountId;
        this.rating = rating;
        this.content = content;
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
