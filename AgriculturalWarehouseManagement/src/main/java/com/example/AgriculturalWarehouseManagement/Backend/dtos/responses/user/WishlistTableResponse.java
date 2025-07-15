package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

public class WishlistTableResponse {
    private int wishlistId;
    private int userId;
    private int productId;

    public WishlistTableResponse() {
    }

    public WishlistTableResponse(int wishlistId, int userId, int productId) {
        this.wishlistId = wishlistId;
        this.userId = userId;
        this.productId = productId;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
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
        return "WishlistAPIResponse{" +
                "wishlistId=" + wishlistId +
                ", userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
