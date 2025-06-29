package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

import java.time.LocalDate;

public class ProductDetailUserResponse {

    private int categoryId;
    private int productId;
    private String productName;
    private String productDescription;
    private String imageUrl;
    private int productDetailId;
    private String productWeight;
    private int batchId;
    private int importedQuantity;
    private int soldQuantity;
    private LocalDate manufactureDate;
    private int expiry;
    private double price;
    private LocalDate expiryDate;
    private int totalAdjustedRemoveQuantity;
    private int remainQuantity;
    private String status;
    private String expiryStatus;
    private int avgRating;
    private int ratingCount;


    public ProductDetailUserResponse() {
    }

    public ProductDetailUserResponse(int categoryId,int productId, String productName, String productDescription, String imageUrl, int productDetailId, String productWeight, int batchId, int importedQuantity, int soldQuantity, LocalDate manufactureDate, int expiry, double price, LocalDate expiryDate, int totalAdjustedRemoveQuantity, int remainQuantity, String status, String expiryStatus, int avgRating, int ratingCount) {
        this.categoryId = categoryId;
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.imageUrl = imageUrl;
        this.productDetailId = productDetailId;
        this.productWeight = productWeight;
        this.batchId = batchId;
        this.importedQuantity = importedQuantity;
        this.soldQuantity = soldQuantity;
        this.manufactureDate = manufactureDate;
        this.expiry = expiry;
        this.price = price;
        this.expiryDate = expiryDate;
        this.totalAdjustedRemoveQuantity = totalAdjustedRemoveQuantity;
        this.remainQuantity = remainQuantity;
        this.status = status;
        this.expiryStatus = expiryStatus;
        this.avgRating = avgRating;
        this.ratingCount = ratingCount;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(int productDetailId) {
        this.productDetailId = productDetailId;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public int getImportedQuantity() {
        return importedQuantity;
    }

    public void setImportedQuantity(int importedQuantity) {
        this.importedQuantity = importedQuantity;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public int getExpiry() {
        return expiry;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setExpiry(int expiry) {
        this.expiry = expiry;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getTotalAdjustedRemoveQuantity() {
        return totalAdjustedRemoveQuantity;
    }

    public void setTotalAdjustedRemoveQuantity(int totalAdjustedRemoveQuantity) {
        this.totalAdjustedRemoveQuantity = totalAdjustedRemoveQuantity;
    }

    public int getRemainQuantity() {
        return remainQuantity;
    }

    public void setRemainQuantity(int remainQuantity) {
        this.remainQuantity = remainQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpiryStatus() {
        return expiryStatus;
    }

    public void setExpiryStatus(String expiryStatus) {
        this.expiryStatus = expiryStatus;
    }

    public int getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(int avgRating) {
        this.avgRating = avgRating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    @Override
    public String toString() {
        return "ProductDetailUserResponse{" +
                "categoryId=" + categoryId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", productDetailId=" + productDetailId +
                ", productWeight='" + productWeight + '\'' +
                ", batchId=" + batchId +
                ", importedQuantity=" + importedQuantity +
                ", soldQuantity=" + soldQuantity +
                ", manufactureDate=" + manufactureDate +
                ", expiry=" + expiry +
                ", price=" + price +
                ", expiryDate=" + expiryDate +
                ", totalAdjustedRemoveQuantity=" + totalAdjustedRemoveQuantity +
                ", remainQuantity=" + remainQuantity +
                ", status='" + status + '\'' +
                ", expiryStatus='" + expiryStatus + '\'' +
                ", avgRating=" + avgRating +
                ", ratingCount=" + ratingCount +
                '}';
    }
}
