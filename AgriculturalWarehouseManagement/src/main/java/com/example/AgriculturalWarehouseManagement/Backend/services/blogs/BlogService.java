package com.example.AgriculturalWarehouseManagement.Backend.services.blogs;

import com.example.AgriculturalWarehouseManagement.Backend.models.Blog;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAllActiveBlogs() {
        // Cách 1: Viết lại query join fetch có điều kiện status
        return blogRepository.findAllActiveWithDetail("Active");
    }

    public Blog getBlogById(Integer id) {
        return blogRepository.findById(id).orElse(null);
    }

    // Thêm method này để lấy N bài viết mới nhất, ví dụ 4 bài
    public List<Blog> getRecentBlogs(int count) {
        return blogRepository.findTopNByStatusOrderByCreatedAtDesc("Active", count);
    }

    public Page<Blog> getActiveBlogsPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return blogRepository.findAllByStatusOrderByCreatedAtDesc("Active", pageable);
    }


}