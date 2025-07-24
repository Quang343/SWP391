package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

public class ProductUserHomepageResponse {

    private int productId;
    private int categoryId;
    private String imageUrlGallery;
    private String productName;
    private int ratingProductDetail;
    private int ratingCount;
    private String productDetailName;
    private double productPrice;
    private String productWeight;
    private String productDescription;


    public ProductUserHomepageResponse() {
    }

    public ProductUserHomepageResponse(int productId, int categoryId, String imageUrlGallery, String productName, int ratingProductDetail, int ratingCount, String productDetailName, double productPrice, String productWeight, String productDescription) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.imageUrlGallery = imageUrlGallery;
        this.productName = productName;
        this.ratingProductDetail = ratingProductDetail;
        this.ratingCount = ratingCount;
        this.productDetailName = productDetailName;
        this.productPrice = productPrice;
        this.productWeight = productWeight;
        this.productDescription = productDescription;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getImageUrlGallery() {
        return imageUrlGallery;
    }

    public void setImageUrlGallery(String imageUrlGallery) {
        this.imageUrlGallery = imageUrlGallery;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getRatingProductDetail() {
        return ratingProductDetail;
    }

    public void setRatingProductDetail(int ratingProductDetail) {
        this.ratingProductDetail = ratingProductDetail;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getProductDetailName() {
        return productDetailName;
    }

    public void setProductDetailName(String productDetailName) {
        this.productDetailName = productDetailName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public String toString() {
        return "ProductUserHomepageResponse{" +
                "productId=" + productId +
                ", categoryId=" + categoryId +
                ", imageUrlGallery='" + imageUrlGallery + '\'' +
                ", productName='" + productName + '\'' +
                ", ratingProductDetail=" + ratingProductDetail +
                ", ratingCount=" + ratingCount +
                ", productDetailName='" + productDetailName + '\'' +
                ", productPrice=" + productPrice +
                ", productWeight='" + productWeight + '\'' +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }
}
