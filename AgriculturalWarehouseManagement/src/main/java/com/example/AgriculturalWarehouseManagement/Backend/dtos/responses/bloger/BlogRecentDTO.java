package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.bloger;

import java.util.Date;

public class BlogRecentDTO {

    private Integer blogID;
    private String title;
    private Date createdAt;
    private String author;
    private String thumbnail;


    public BlogRecentDTO() {
    }

    public BlogRecentDTO(Integer blogID, String title, Date createdAt, String author, String thumbnail) {}

    public Integer getBlogID() {
        return blogID;
    }

    public void setBlogID(Integer blogID) {
        this.blogID = blogID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
