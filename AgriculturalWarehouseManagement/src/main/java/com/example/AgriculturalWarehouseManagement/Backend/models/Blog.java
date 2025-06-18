package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blogid")
    private Integer blogID;

    @Column(name = "userid")
    private Integer userID;

    @Column(name = "blogcategoryid")
    private Integer blogCategoryID;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "createdat")
    private Date createdAt;

    @Column(name = "blogdateupdate")
    private Date blogDateUpdate;

    @Column(name = "status")
    private String status;

    @Column(name = "author")
    private String author;

    public Integer getBlogID() {
        return blogID;
    }

    public void setBlogID(Integer blogID) {
        this.blogID = blogID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getBlogCategoryID() {
        return blogCategoryID;
    }

    public void setBlogCategoryID(Integer blogCategoryID) {
        this.blogCategoryID = blogCategoryID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getBlogDateUpdate() {
        return blogDateUpdate;
    }

    public void setBlogDateUpdate(Date blogDateUpdate) {
        this.blogDateUpdate = blogDateUpdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogID=" + blogID +
                ", userID=" + userID +
                ", blogCategoryID=" + blogCategoryID +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", blogDateUpdate=" + blogDateUpdate +
                ", status='" + status + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

