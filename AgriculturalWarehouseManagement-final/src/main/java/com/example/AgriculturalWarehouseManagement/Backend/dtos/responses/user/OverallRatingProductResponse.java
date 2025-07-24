package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

public class OverallRatingProductResponse {

    private String overallRating;
    private int ratingFive;
    private int ratingFour;
    private int ratingThree;
    private int ratingTwo;
    private int ratingOne;

    public OverallRatingProductResponse() {
    }

    public OverallRatingProductResponse(String overallRating, int ratingFive, int ratingFour, int ratingThree, int ratingTwo, int ratingOne) {
        this.overallRating = overallRating;
        this.ratingFive = ratingFive;
        this.ratingFour = ratingFour;
        this.ratingThree = ratingThree;
        this.ratingTwo = ratingTwo;
        this.ratingOne = ratingOne;
    }

    public String getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(String overallRating) {
        this.overallRating = overallRating;
    }

    public int getRatingFive() {
        return ratingFive;
    }

    public void setRatingFive(int ratingFive) {
        this.ratingFive = ratingFive;
    }

    public int getRatingFour() {
        return ratingFour;
    }

    public void setRatingFour(int ratingFour) {
        this.ratingFour = ratingFour;
    }

    public int getRatingThree() {
        return ratingThree;
    }

    public void setRatingThree(int ratingThree) {
        this.ratingThree = ratingThree;
    }

    public int getRatingTwo() {
        return ratingTwo;
    }

    public void setRatingTwo(int ratingTwo) {
        this.ratingTwo = ratingTwo;
    }

    public int getRatingOne() {
        return ratingOne;
    }

    public void setRatingOne(int ratingOne) {
        this.ratingOne = ratingOne;
    }

    @Override
    public String toString() {
        return "OverallRatingProductResponse{" +
                "overallRating='" + overallRating + '\'' +
                ", ratingFive=" + ratingFive +
                ", ratingFour=" + ratingFour +
                ", ratingThree=" + ratingThree +
                ", ratingTwo=" + ratingTwo +
                ", ratingOne=" + ratingOne +
                '}';
    }
}
