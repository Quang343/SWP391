package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

public class GalleryUserResponse {
    private int galleryId;
    private int productId;
    private String imageUrl;

    public GalleryUserResponse() {
    }

    public GalleryUserResponse(int galleryId, int productId, String imageUrl) {
        this.galleryId = galleryId;
        this.productId = productId;
        this.imageUrl = imageUrl;
    }

    public int getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(int galleryId) {
        this.galleryId = galleryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "GalleryUserResponse{" +
                "galleryId=" + galleryId +
                ", productId=" + productId +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
