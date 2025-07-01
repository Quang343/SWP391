package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

public class CartTableUserResponse {
    private int cartId;
    private int userId;
    private int productDetailId;
    private int quantity;
    private double price;

    public CartTableUserResponse() {
    }

    public CartTableUserResponse(int cartId, int userId, int productDetailId, int quantity, double price) {
        this.cartId = cartId;
        this.userId = userId;
        this.productDetailId = productDetailId;
        this.quantity = quantity;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CartUserResponse{" +
                "cartId=" + cartId +
                ", userId=" + userId +
                ", productDetailId=" + productDetailId +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
