package com.example.AgriculturalWarehouseManagement.Backend.services.bloger;



import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.bloger.BlogRecentDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Blog;
import com.example.AgriculturalWarehouseManagement.Backend.models.BlogStatus;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.bloger.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@author: Đào Huy Hoàng

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public List<Blog> getAllActiveBlogs() {
        return blogRepository.findAllActiveWithDetail(BlogStatus.ACTIVE);
    }

    @Transactional(readOnly = true)
    public Blog getBlogById(Integer id) {
        return blogRepository.findByIdWithDetail(id);
    }


    // Thêm method này để lấy N bài viết mới nhất, ví dụ 4 bài
    public List<BlogRecentDTO> getRecentBlogs(int count) {
        List<Object[]> results = blogRepository.findTopNByStatus("ACTIVE", count);
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
        return blogRepository.findAllByStatusOrderByCreatedAtDesc(BlogStatus.ACTIVE, pageable);
    }

    public Page<Blog> searchBlogs(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return blogRepository.searchByAuthorOrContent(BlogStatus.ACTIVE, keyword, pageable);
    }

    // User CRUD
    // Lấy blog cá nhân (không phân trang)
    public List<Blog> getBlogsByUser(Long userId) {
        return blogRepository.findByUserIdWithDetail(userId);
    }

    public Page<Blog> getBlogsByUserPage(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return blogRepository.findByUserIDAndStatusOrderByCreatedAtDesc(userId, BlogStatus.ACTIVE, pageable);
    }

    public List<Blog> findByUserId(Long userId) {
        return blogRepository.findByUserID(userId);
    }

    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    public Page<Blog> getAllStatusBlogsByUserPage(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return blogRepository.findByUserIDOrderByCreatedAtDesc(userId, pageable);
    }

    public void deleteById(Integer id) {
        Blog blog = blogRepository.findById(id).orElse(null);
        if (blog != null) {
            blog.setStatus(BlogStatus.DELETED);
            blogRepository.save(blog);
        }
    }


    public Page<Blog> getAllBlogsPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        // method này có @EntityGraph → fetch blogCategory đầy đủ
        return blogRepository.findAll(pageable);
    }

    public Page<Blog> getBlogsByUserAndStatusPage(Long userId, BlogStatus status, int page, int size) {
        return blogRepository.findByUserIDAndStatusOrderByCreatedAtDesc(userId, status, PageRequest.of(page, size));
    }

    public Page<Blog> getAllBlogsPageByStatus(int page, int size, BlogStatus status) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return blogRepository.findAllByStatus(status, pageable);
    }

    public Long getNumberBlogUser(int userId) {
        Long numberOfBlogs = blogRepository.countBlogsByUserId(userId);
        return numberOfBlogs;
    }

}