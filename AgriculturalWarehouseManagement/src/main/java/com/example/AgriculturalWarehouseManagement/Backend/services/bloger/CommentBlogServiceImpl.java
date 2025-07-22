package com.example.AgriculturalWarehouseManagement.Backend.services.bloger;
import com.example.AgriculturalWarehouseManagement.Backend.models.CommentBlog;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.bloger.CommentBlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//@author: Đào Huy Hoàng

@Service
@RequiredArgsConstructor
public class CommentBlogServiceImpl implements CommentBlogService {

    private final CommentBlogRepository commentBlogRepository;

    @Override
    public List<CommentBlog> getCommentsByBlogId(Integer blogId) {
        return commentBlogRepository.findByBlog_BlogID(blogId);
    }

    @Override
    public CommentBlog save(CommentBlog comment) {
        return commentBlogRepository.save(comment);
    }
}