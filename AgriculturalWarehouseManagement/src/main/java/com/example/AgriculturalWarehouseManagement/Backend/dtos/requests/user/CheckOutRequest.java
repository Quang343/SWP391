package com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user;

public class CheckOutRequest {
    private int addressId;
    private double subTotal;
    private int voucherId;
    private double percentDiscount;
    private double totalPrice;

    public CheckOutRequest() {
    }


    public CheckOutRequest(int addressId,double subTotal, int voucherId, double percentDiscount, double totalPrice) {
        this.addressId = addressId;
        this.subTotal = subTotal;
        this.voucherId = voucherId;
        this.percentDiscount = percentDiscount;
        this.totalPrice = totalPrice;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public double getPercentDiscount() {
        return percentDiscount;
    }

    public void setPercentDiscount(double percentDiscount) {
        this.percentDiscount = percentDiscount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CheckOutRequest{" +
                "addressId=" + addressId +
                ", subTotal=" + subTotal +
                ", voucherId=" + voucherId +
                ", percentDiscount=" + percentDiscount +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
