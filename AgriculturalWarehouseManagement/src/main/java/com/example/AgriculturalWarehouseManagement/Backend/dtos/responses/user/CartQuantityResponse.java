package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

import java.time.LocalDateTime;

public class CartQuantityResponse {

    private int cartId;
    private int userId;
    private int productDetailId;
    private int quantity;
    private double totalPrice;
    private LocalDateTime createdAt;
    private int minutes;

    public CartQuantityResponse(int cartId, int userId, int productDetailId, int quantity, double totalPrice, LocalDateTime createdAt, int minutes) {
        this.cartId = cartId;
        this.userId = userId;
        this.productDetailId = productDetailId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.minutes = minutes;
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

    public int getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(int productDetailId) {
        this.productDetailId = productDetailId;
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

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        return "CartQuantityResponse{" +
                "cartId=" + cartId +
                ", userId=" + userId +
                ", productDetailId=" + productDetailId +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", createdAt=" + createdAt +
                ", minutes=" + minutes +
                '}';
    }
}
