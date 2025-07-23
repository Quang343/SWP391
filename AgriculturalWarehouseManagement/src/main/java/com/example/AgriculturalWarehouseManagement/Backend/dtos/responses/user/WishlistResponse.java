package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

public class WishlistResponse {
    private int userId;
    private int productId;

    public WishlistResponse() {
    }

    public WishlistResponse(int userId, int productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "WishlistResponse{" +
                "userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
