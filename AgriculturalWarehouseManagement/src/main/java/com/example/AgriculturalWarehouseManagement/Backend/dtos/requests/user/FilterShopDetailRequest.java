package com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user;

import java.util.List;

public class FilterShopDetailRequest {
    private List<Integer> ratings;
    private List<Integer> categoryIds;
    private Integer minPrice;
    private Integer maxPrice;
    private String sortBy;

    public FilterShopDetailRequest() {
    }

    public FilterShopDetailRequest(List<Integer> ratings, List<Integer> categoryIds, Integer minPrice, Integer maxPrice, String sortBy) {
        this.ratings = ratings;
        this.categoryIds = categoryIds;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.sortBy = sortBy;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(List<Integer> ratings) {
        this.ratings = ratings;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public String toString() {
        return "FilterShopDetailRequest{" +
                "ratings=" + ratings +
                ", categoryIds=" + categoryIds +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", sortBy='" + sortBy + '\'' +
                '}';
    }
}

