package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

public class CheckOutProductsResponse {
    private int userId;
    private int productId;
    private int productDetailId;
    private String imageUrl;
    private String productName;
    private String sellerName;
    private String productWeight;
    private double productPrice;
    private int totalQuantityProductDetail;
    private double totalPriceProductDetail;

    public CheckOutProductsResponse() {
    }

    public CheckOutProductsResponse(int userId, int productId, int productDetailId, String imageUrl, String productName, String sellerName, String productWeight, double productPrice, int totalQuantityProductDetail, double totalPriceProductDetail) {
        this.userId = userId;
        this.productId = productId;
        this.productDetailId = productDetailId;
        this.imageUrl = imageUrl;
        this.productName = productName;
        this.sellerName = sellerName;
        this.productWeight = productWeight;
        this.productPrice = productPrice;
        this.totalQuantityProductDetail = totalQuantityProductDetail;
        this.totalPriceProductDetail = totalPriceProductDetail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(int productDetailId) {
        this.productDetailId = productDetailId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getTotalQuantityProductDetail() {
        return totalQuantityProductDetail;
    }

    public void setTotalQuantityProductDetail(int totalQuantityProductDetail) {
        this.totalQuantityProductDetail = totalQuantityProductDetail;
    }

    public double getTotalPriceProductDetail() {
        return totalPriceProductDetail;
    }

    public void setTotalPriceProductDetail(double totalPriceProductDetail) {
        this.totalPriceProductDetail = totalPriceProductDetail;
    }

    @Override
    public String toString() {
        return "CheckOutProductResponse{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", productDetailId=" + productDetailId +
                ", imageUrl='" + imageUrl + '\'' +
                ", productName='" + productName + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", productWeight='" + productWeight + '\'' +
                ", productPrice=" + productPrice +
                ", totalQuantityProductDetail=" + totalQuantityProductDetail +
                ", totalPriceProductDetail=" + totalPriceProductDetail +
                '}';
    }
}
