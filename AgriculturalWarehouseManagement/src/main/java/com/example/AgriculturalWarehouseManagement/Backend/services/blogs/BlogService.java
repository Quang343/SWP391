package com.example.AgriculturalWarehouseManagement.Backend.services.blogs;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.response.admin.blog.BlogRecentDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Blog;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAllActiveBlogs() {
        return blogRepository.findAllActiveWithDetail("Active");
    }

    public Blog getBlogById(Integer id) {
        return blogRepository.findById(id).orElse(null);
    }

    // Thêm method này để lấy N bài viết mới nhất, ví dụ 4 bài
    public List<BlogRecentDTO> getRecentBlogs(int count) {
        List<Object[]> results = blogRepository.findTopNByStatusOrderByCreatedAtDescWithThumbnail("Active", count);
        List<BlogRecentDTO> dtos = new ArrayList<>();
        for (Object[] row : results) {
            BlogRecentDTO dto = new BlogRecentDTO();
            dto.setBlogID((Integer) row[0]);
            dto.setTitle((String) row[3]);
            dto.setCreatedAt((Date) row[5]);
            dto.setAuthor((String) row[8]);
            dto.setThumbnail((String) row[9]); // Vị trí thumbnail, đếm theo thứ tự cột
            dtos.add(dto);
        }
        return dtos;
    }


    public Page<Blog> getActiveBlogsPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return blogRepository.findAllByStatusOrderByCreatedAtDesc("Active", pageable);
    }


}