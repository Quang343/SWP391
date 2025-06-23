package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "blogdetail")
public class BlogDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blogdetailid")
    private Integer blogDetailID;

    // Khóa ngoại tham chiếu tới Blog
    @OneToOne
    @JoinColumn(name = "blogid", referencedColumnName = "blogid")
    private Blog blog;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "detailcontent")
    private String detailContent;

    public Integer getBlogDetailID() {
        return blogDetailID;
    }

    public void setBlogDetailID(Integer blogDetailID) {
        this.blogDetailID = blogDetailID;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
    }
}
