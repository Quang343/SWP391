package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;


public class WeightCompareProductDetailsResponse {

    private int productDetailId;
    private int productId;
    private int categoryWeightId;
    private int categoryId;
    private int expiry;
    private double price;
    private String productWeight;

    public WeightCompareProductDetailsResponse() {
    }

    public WeightCompareProductDetailsResponse(int productDetailId, int productId, int categoryWeightId, int categoryId, int expiry, double price, String productWeight) {
        this.productDetailId = productDetailId;
        this.productId = productId;
        this.categoryWeightId = categoryWeightId;
        this.categoryId = categoryId;
        this.expiry = expiry;
        this.price = price;
        this.productWeight = productWeight;
    }

    public int getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(int productDetailId) {
        this.productDetailId = productDetailId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryWeightId() {
        return categoryWeightId;
    }

    public void setCategoryWeightId(int categoryWeightId) {
        this.categoryWeightId = categoryWeightId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getExpiry() {
        return expiry;
    }

    public void setExpiry(int expiry) {
        this.expiry = expiry;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    @Override
    public String toString() {
        return "WeightCompareProductDetailsResponse{" +
                "productDetailId=" + productDetailId +
                ", productId=" + productId +
                ", categoryWeightId=" + categoryWeightId +
                ", categoryId=" + categoryId +
                ", expiry=" + expiry +
                ", productWeight='" + productWeight + '\'' +
                '}';
    }
}
