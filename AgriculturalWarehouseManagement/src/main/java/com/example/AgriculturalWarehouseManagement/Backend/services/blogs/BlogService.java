package com.example.AgriculturalWarehouseManagement.Backend.services.blogs;

import com.example.AgriculturalWarehouseManagement.Backend.models.Blog;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BlogService implements  IBlogService{

    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAllActiveBlogs() {
        return blogRepository.findAllByStatus("Active");
    }

    public Blog getBlogById(Integer id) {
        return blogRepository.findById(id).orElse(null);
    }
    // Thêm method này để lấy N bài viết mới nhất, ví dụ 4 bài
    public List<Blog> getRecentBlogs(int count) {
        return blogRepository.findTopNByStatusOrderByCreatedAtDesc("Active", count);
    }

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }
}