//package com.example.AgriculturalWarehouseManagement.Backend.controllers.blog;
//
//import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.blog.BlogDTO;
//import com.example.AgriculturalWarehouseManagement.Backend.models.Blog;
//import com.example.AgriculturalWarehouseManagement.Backend.services.blog.BlogService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
////@author: Đào Huy Hoàng
//
//@RestController
//@RequestMapping("/api/admin/blog")
//@RequiredArgsConstructor
//public class AdminBlogRestController {
//
//    private final BlogService blogService;
//
//
//    @GetMapping("/page")
//    public ResponseEntity<?> getAllBlogsPage(
//            @RequestParam(defaultValue = "1") int page,
//            @RequestParam(defaultValue = "5") int size
//    ) {
//        // Lấy tất cả blog, phân trang, không lọc theo user
//        Page<Blog> blogPage = blogService.getAllBlogsPage(page - 1, size);
//
//        // Có thể bổ sung thêm categoryName nếu muốn show trong bảng admin
//        List<BlogDTO> blogDTOs = blogPage.getContent().stream().map(blog -> {
//            BlogDTO dto = new BlogDTO();
//            dto.setBlogID(blog.getBlogID());
//            dto.setTitle(blog.getTitle());
//            dto.setContent(blog.getContent());
//            dto.setStatus(blog.getStatus().toString());
//            dto.setAuthor(blog.getAuthor());
//            dto.setCreatedAt(blog.getCreatedAt() != null ? blog.getCreatedAt().toString() : null);
//            if (blog.getBlogDetail() != null) {
//                dto.setThumbnail(blog.getBlogDetail().getThumbnail());
//            }
//            // Nếu muốn show tên danh mục trong bảng admin
//            if (blog.getBlogCategory() != null) {
//                dto.setBlogCategoryName(blog.getBlogCategory().getCategoryName());
//            }
//            return dto;
//        }).toList();
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("blogs", blogDTOs);
//        result.put("totalPages", blogPage.getTotalPages());
//        result.put("currentPage", blogPage.getNumber() + 1);
//
//        return ResponseEntity.ok(result);
//    }
//}
//
