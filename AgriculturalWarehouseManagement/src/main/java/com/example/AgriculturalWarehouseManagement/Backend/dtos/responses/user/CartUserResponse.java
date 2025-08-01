package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CartUserResponse {

    private int cartId;
    private int userId;
    private int productId;
    private int productDetailId;
    private String imageUrl;
    private String productName;
    private String sellerName;
    private String productWeight;
    private double productPrice;
    private int quantity;
    private double totalPrice;
    private LocalDateTime createdAt;

    public CartUserResponse() {
    }


    public CartUserResponse(int cartId, int userId, int productId, int productDetailId, String imageUrl, String productName, String sellerName, String productWeight, double productPrice, int quantity, double totalPrice, LocalDateTime createdAt) {
        this.cartId = cartId;
        this.userId = userId;
        this.productId = productId;
        this.productDetailId = productDetailId;
        this.imageUrl = imageUrl;
        this.productName = productName;
        this.sellerName = sellerName;
        this.productWeight = productWeight;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
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

    public int getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(int productDetailId) {
        this.productDetailId = productDetailId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String formatCreateAt() {
        if (createdAt != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return createdAt.format(formatter);  // Format without the "T"
        }
        return null;  // Return null if createAt is null
    }

    @Override
    public String toString() {
        return "CartUserResponse{" +
                "cartId=" + cartId +
                ", userId=" + userId +
                ", productId=" + productId +
                ", productDetailId=" + productDetailId +
                ", imageUrl='" + imageUrl + '\'' +
                ", productName='" + productName + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", productWeight='" + productWeight + '\'' +
                ", productPrice=" + productPrice +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", createdAt=" + createdAt +
                '}';
    }
}
