package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

import java.time.LocalDateTime;

public class VoucherUsersResponse {

    private int voucherUserId;
    private String discountType;
    private double discountValue;
    private LocalDateTime endDate;
    private String isLocked;
    private double minOrderAmount;
    private int quantity;
    private LocalDateTime startDate;
    private String status;
    private int usedQuantity;
    private String voucherCode;
    private int remainQuantity;
    private int remainDays;

    public VoucherUsersResponse(int voucherUserId, String discountType, double discountValue, LocalDateTime endDate, String isLocked, double minOrderAmount, int quantity, LocalDateTime startDate, String status, int usedQuantity, String voucherCode, int remainQuantity, int remainDays) {
        this.voucherUserId = voucherUserId;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.endDate = endDate;
        this.isLocked = isLocked;
        this.minOrderAmount = minOrderAmount;
        this.quantity = quantity;
        this.startDate = startDate;
        this.status = status;
        this.usedQuantity = usedQuantity;
        this.voucherCode = voucherCode;
        this.remainQuantity = remainQuantity;
        this.remainDays = remainDays;
    }

    public int getVoucherUserId() {
        return voucherUserId;
    }

    public void setVoucherUserId(int voucherUserId) {
        this.voucherUserId = voucherUserId;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    public double getMinOrderAmount() {
        return minOrderAmount;
    }

    public void setMinOrderAmount(double minOrderAmount) {
        this.minOrderAmount = minOrderAmount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUsedQuantity() {
        return usedQuantity;
    }

    public void setUsedQuantity(int usedQuantity) {
        this.usedQuantity = usedQuantity;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public int getRemainQuantity() {
        return remainQuantity;
    }

    public void setRemainQuantity(int remainQuantity) {
        this.remainQuantity = remainQuantity;
    }

    public int getRemainDays() {
        return remainDays;
    }

    public void setRemainDays(int remainDays) {
        this.remainDays = remainDays;
    }

    @Override
    public String toString() {
        return "VoucherUsersResponse{" +
                "voucherUserId=" + voucherUserId +
                ", discountType='" + discountType + '\'' +
                ", discountValue=" + discountValue +
                ", endDate='" + endDate + '\'' +
                ", isLocked='" + isLocked + '\'' +
                ", minOrderAmount=" + minOrderAmount +
                ", quantity=" + quantity +
                ", startDate='" + startDate + '\'' +
                ", status='" + status + '\'' +
                ", usedQuantity=" + usedQuantity +
                ", voucherCode='" + voucherCode + '\'' +
                ", remainQuantity=" + remainQuantity +
                ", remainDays=" + remainDays +
                '}';
    }
}
