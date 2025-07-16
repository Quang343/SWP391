package com.example.AgriculturalWarehouseManagement.Backend.controllers.bloger;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.bloger.AdminBlogDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.bloger.BlogDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Blog;
import com.example.AgriculturalWarehouseManagement.Backend.models.BlogCategory;

import com.example.AgriculturalWarehouseManagement.Backend.models.BlogStatus;
import com.example.AgriculturalWarehouseManagement.Backend.services.bloger.BlogCategoryService;
import com.example.AgriculturalWarehouseManagement.Backend.services.bloger.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Tạo Controller AdminBlogAPIController cho Admin
@RestController
@RequestMapping("/api/admin/blog")
@RequiredArgsConstructor
public class AdminBlogAPIController {

    private final BlogService blogService;
    private final BlogCategoryService blogCategoryService;

    // API lấy danh sách tất cả blog phân trang cho admin
    @GetMapping("/page")
    public ResponseEntity<?> getAllBlogsPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        // Lấy tất cả các blog phân trang
        Page<Blog> blogPage = blogService.getAllBlogsPage(page - 1, size);

        List<AdminBlogDTO> blogDTOs = blogPage.getContent().stream().map(blog -> {
            AdminBlogDTO dto = new AdminBlogDTO();
            dto.setBlogID(blog.getBlogID());
            dto.setTitle(blog.getTitle());
            dto.setContent(blog.getContent());
            dto.setStatus(blog.getStatus().toString());
            dto.setAuthor(blog.getAuthor());
            dto.setCreatedAt(blog.getCreatedAt() != null ? blog.getCreatedAt().toString() : null);
            if (blog.getBlogDetail() != null) {
                dto.setThumbnail(blog.getBlogDetail().getThumbnail());
            }
            if (blog.getBlogCategory() != null) {
                dto.setBlogCategoryName(blog.getBlogCategory().getCategoryName());
                dto.setBlogCategoryID(blog.getBlogCategory().getBlogCategoryId());
            }
            return dto;
        }).toList();


        // Trả về response với dữ liệu blog
        Map<String, Object> result = new HashMap<>();
        result.put("blogs", blogDTOs);
        result.put("totalPages", blogPage.getTotalPages());
        result.put("currentPage", blogPage.getNumber() + 1);

        return ResponseEntity.ok(result);
    }


}
