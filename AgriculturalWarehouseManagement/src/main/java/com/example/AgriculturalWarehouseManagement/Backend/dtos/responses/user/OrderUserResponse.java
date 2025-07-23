package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderUserResponse {
    private int orderId;
    private String status;
    private LocalDateTime orderDate;
    private String orderCode;
    private String email;
    private double totalPrice;
    private int totalQuantity;

    public OrderUserResponse(int orderId, String status, LocalDateTime orderDate, String orderCode, String email,double totalPrice, int totalQuantity) {
        this.orderId = orderId;
        this.status = status;
        this.orderDate = orderDate;
        this.orderCode = orderCode;
        this.email = email;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
    }

    public String formatOrderDate() {
        if (orderDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return orderDate.format(formatter);  // Format without the "T"
        }
        return null;  // Return null if createAt is null
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @Override
    public String toString() {
        return "OrderUserResponse{" +
                "orderId=" + orderId +
                ", status='" + status + '\'' +
                ", orderDate=" + orderDate +
                ", orderCode='" + orderCode + '\'' +
                ", email='" + email + '\'' +
                ", totalPrice=" + totalPrice +
                ", totalQuantity=" + totalQuantity +
                '}';
    }
}
