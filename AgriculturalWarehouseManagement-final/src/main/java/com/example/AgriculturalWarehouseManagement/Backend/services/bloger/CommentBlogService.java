package com.example.AgriculturalWarehouseManagement.Backend.services.bloger;

import com.example.AgriculturalWarehouseManagement.Backend.models.CommentBlog;

import java.util.List;

//@author: Đào Huy Hoàng

public interface CommentBlogService {
    // Lấy comments theo Blog ID
    List<CommentBlog> getCommentsByBlogId(Integer blogId);

    // Lưu comment vào database
    CommentBlog save(CommentBlog comment);

    // Nếu cần có thể thêm update, delete
}
