package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderDetailUserResponse {
    private int orderId;
    private String addressShip;
    private double discountAmount;
    private String emailShip;
    private double finalAmount;
    private String fullNameShip;
    private String noteShip;
    private String orderCode;
    private LocalDateTime orderDate;
    private String phoneShip;
    private String orderStatus;
    private double totalAmount;
    private String voucherCode;
    private int productDetailId;
    private String productName;
    private double productTotalPrice;
    private int quantity;
    private String sellerName;
    private String productWeight;
    private double productPrice;
    private String imageUrl;
    private int productId;
    private String userNameUser;
    private String userEmail;
    private String userAddress;
    private String userPhone;

    public OrderDetailUserResponse(int orderId, String addressShip, double discountAmount, String emailShip, double finalAmount, String fullNameShip, String noteShip, String orderCode, LocalDateTime orderDate, String phoneShip, String orderStatus, double totalAmount, String voucherCode, int productDetailId, String productName, double productTotalPrice, int quantity, String sellerName, String productWeight, double productPrice, String imageUrl, int productId, String userNameUser, String userEmail, String userAddress, String userPhone) {
        this.orderId = orderId;
        this.addressShip = addressShip;
        this.discountAmount = discountAmount;
        this.emailShip = emailShip;
        this.finalAmount = finalAmount;
        this.fullNameShip = fullNameShip;
        this.noteShip = noteShip;
        this.orderCode = orderCode;
        this.orderDate = orderDate;
        this.phoneShip = phoneShip;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.voucherCode = voucherCode;
        this.productDetailId = productDetailId;
        this.productName = productName;
        this.productTotalPrice = productTotalPrice;
        this.quantity = quantity;
        this.sellerName = sellerName;
        this.productWeight = productWeight;
        this.productPrice = productPrice;
        this.imageUrl = imageUrl;
        this.productId = productId;
        this.userNameUser = userNameUser;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
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

    public String getAddressShip() {
        return addressShip;
    }

    public void setAddressShip(String addressShip) {
        this.addressShip = addressShip;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getEmailShip() {
        return emailShip;
    }

    public void setEmailShip(String emailShip) {
        this.emailShip = emailShip;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public String getFullNameShip() {
        return fullNameShip;
    }

    public void setFullNameShip(String fullNameShip) {
        this.fullNameShip = fullNameShip;
    }

    public String getNoteShip() {
        return noteShip;
    }

    public void setNoteShip(String noteShip) {
        this.noteShip = noteShip;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getPhoneShip() {
        return phoneShip;
    }

    public void setPhoneShip(String phoneShip) {
        this.phoneShip = phoneShip;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public int getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(int productDetailId) {
        this.productDetailId = productDetailId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(double productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getUserNameUser() {
        return userNameUser;
    }

    public void setUserNameUser(String userNameUser) {
        this.userNameUser = userNameUser;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "OrderDetailUserResponse{" +
                "orderId=" + orderId +
                ", addressShip='" + addressShip + '\'' +
                ", discountAmount=" + discountAmount +
                ", emailShip='" + emailShip + '\'' +
                ", finalAmount=" + finalAmount +
                ", fullNameShip='" + fullNameShip + '\'' +
                ", noteShip='" + noteShip + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", orderDate=" + orderDate +
                ", phoneShip='" + phoneShip + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", totalAmount=" + totalAmount +
                ", voucherCode='" + voucherCode + '\'' +
                ", productDetailId=" + productDetailId +
                ", productName='" + productName + '\'' +
                ", productTotalPrice=" + productTotalPrice +
                ", quantity=" + quantity +
                ", sellerName='" + sellerName + '\'' +
                ", productWeight='" + productWeight + '\'' +
                ", productPrice=" + productPrice +
                ", imageUrl='" + imageUrl + '\'' +
                ", productId=" + productId +
                ", userNameUser='" + userNameUser + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }
}
