package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

public class ProductUserHomepageResponse {

    private int productId;
    private String imageUrlGallery;
    private String productName;
    private int ratingProductDetail;
    private int ratingCount;
    private String productDetailName;
    private double productPrice;
    private double productWeight;
    private String productDescription;


    public ProductUserHomepageResponse() {
    }

    public ProductUserHomepageResponse(int productId, String imageUrlGallery, String productName, int ratingProductDetail, int ratingCount, String productDetailName, double productPrice, double productWeight, String productDescription) {
        this.productId = productId;
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

    public String getProductDetailName() {
        return productDetailName;
    }

    public void setProductDetailName(String productDetailName) {
        this.productDetailName = productDetailName;
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

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(double productWeight) {
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
                ", imageUrlGallery='" + imageUrlGallery + '\'' +
                ", productName='" + productName + '\'' +
                ", ratingProductDetail=" + ratingProductDetail +
                ", ratingCount=" + ratingCount +
                ", productDetailName='" + productDetailName + '\'' +
                ", productPrice=" + productPrice +
                ", productWeight=" + productWeight +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }
}
