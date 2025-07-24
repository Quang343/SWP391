package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

public class TotalCartUserResponse {
    private int userId;
    private double totalCart;

    public TotalCartUserResponse() {
    }

    public TotalCartUserResponse(int userId, double totalCart) {
        this.userId = userId;
        this.totalCart = totalCart;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalCart() {
        return totalCart;
    }

    public void setTotalCart(double totalCart) {
        this.totalCart = totalCart;
    }

    @Override
    public String toString() {
        return "TotalCartUserResponse{" +
                "userId=" + userId +
                ", totalCart=" + totalCart +
                '}';
    }
}
