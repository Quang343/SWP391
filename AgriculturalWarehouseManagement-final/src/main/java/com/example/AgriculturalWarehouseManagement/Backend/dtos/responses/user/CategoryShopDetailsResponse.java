package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

public class CategoryShopDetailsResponse {

    private int categoryId;
    private String categoryName;
    private String description;
    private String status;
    private String imageUrl;
    private int totalProducts;

    public CategoryShopDetailsResponse() {
    }

    public CategoryShopDetailsResponse(int categoryId, String categoryName, String description, String status, String imageUrl, int totalProducts) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.status = status;
        this.imageUrl = imageUrl;
        this.totalProducts = totalProducts;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    @Override
    public String toString() {
        return "CategoryShopDetailsResponse{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", totalProducts=" + totalProducts +
                '}';
    }
}
