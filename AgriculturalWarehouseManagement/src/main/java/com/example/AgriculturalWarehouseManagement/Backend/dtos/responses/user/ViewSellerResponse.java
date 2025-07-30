package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

public class ViewSellerResponse {
    private int id;
    private int productDetailId;
    private String emailSeller;

    public ViewSellerResponse(int id, int productDetailId, String emailSeller) {
        this.id = id;
        this.productDetailId = productDetailId;
        this.emailSeller = emailSeller;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(int productDetailId) {
        this.productDetailId = productDetailId;
    }

    public String getEmailSeller() {
        return emailSeller;
    }

    public void setEmailSeller(String emailSeller) {
        this.emailSeller = emailSeller;
    }

    @Override
    public String toString() {
        return "ViewSellerResponse{" +
                "id=" + id +
                ", productDetailId=" + productDetailId +
                ", emailSeller='" + emailSeller + '\'' +
                '}';
    }
}
