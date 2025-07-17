package com.example.AgriculturalWarehouseManagement.Backend.controllers.bloger;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.bloger.AdminBlogDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.bloger.BlogDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Blog;
import com.example.AgriculturalWarehouseManagement.Backend.models.BlogCategory;

import com.example.AgriculturalWarehouseManagement.Backend.models.BlogDetail;
import com.example.AgriculturalWarehouseManagement.Backend.models.BlogStatus;
import com.example.AgriculturalWarehouseManagement.Backend.services.bloger.BlogCategoryService;
import com.example.AgriculturalWarehouseManagement.Backend.services.bloger.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

// Tạo Controller AdminBlogAPIController cho Admin
@RestController
@RequestMapping("/api/admin/blog")
@RequiredArgsConstructor
public class AdminBlogAPIController {

    private final BlogService blogService;
    private final BlogCategoryService blogCategoryService;

    @Value("${app.upload.product-dir}")
    private String uploadDir;

    @GetMapping("/page")
    public ResponseEntity<?> getAllBlogsPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
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
            // CHUẨN: mapping tên & id category, nếu object null thì query thêm
            if (blog.getBlogCategory() != null) {
                dto.setBlogCategoryName(blog.getBlogCategory().getCategoryName());
                dto.setBlogCategoryID(blog.getBlogCategory().getBlogCategoryId());
            } else if (blog.getBlogCategoryID() != null) {
               // BlogCategory cate = blogCategoryService.findById(Long.valueOf(blog.getBlogCategoryID()));
                BlogCategory cate = blogCategoryService.findById(blog.getBlogCategoryID());

                dto.setBlogCategoryName(cate != null ? cate.getCategoryName() : "");
                dto.setBlogCategoryID(cate != null ? cate.getBlogCategoryId() : null);
            }
            return dto;
        }).toList();

        Map<String, Object> result = new HashMap<>();
        result.put("blogs", blogDTOs);
        result.put("totalPages", blogPage.getTotalPages());
        result.put("currentPage", blogPage.getNumber() + 1);

        return ResponseEntity.ok(result);
    }



    @GetMapping("/category")
    public ResponseEntity<?> getAllBlogCategories() {
        List<BlogCategory> list = blogCategoryService.findAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/status-list")
    public ResponseEntity<?> getStatusList() {
        List<Map<String, String>> statusList = Arrays.stream(BlogStatus.values())
                .map(s -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("name", s.name());
                    map.put("label", s.getStatus());
                    return map;
                })
                .toList();
        return ResponseEntity.ok(statusList);
    }

    @PostMapping("/upload-thumbnail")
    public ResponseEntity<?> uploadThumbnail(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded!");
        }
        try {
            String fileName = saveThumbnailFile(file);
            return ResponseEntity.ok(fileName);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload error: " + e.getMessage());
        }
    }

    // Có thể copy nguyên hàm này từ BlogRestController
    private String saveThumbnailFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path uploadPath = Paths.get(uploadDir, "Blog");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        Files.copy(file.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

    @PostMapping("/add_blog")
    public ResponseEntity<?> addAdminBlog(@RequestBody AdminBlogDTO dto) {
        Blog blog = new Blog();
        blog.setTitle(dto.getTitle());
        blog.setContent(dto.getContent());
        blog.setAuthor(dto.getAuthor());
        blog.setStatus(BlogStatus.valueOf(dto.getStatus()));
        blog.setCreatedAt(new java.util.Date());

        if (dto.getBlogCategoryID() != null) {
            // Lấy object category và gán cả object + id
            BlogCategory blogCategory = blogCategoryService.findById(dto.getBlogCategoryID());
            blog.setBlogCategory(blogCategory);
            blog.setBlogCategoryID(dto.getBlogCategoryID().intValue());
        }

        // Nếu có thumbnail, tạo BlogDetail
        if (dto.getThumbnail() != null && !dto.getThumbnail().isEmpty()) {
            BlogDetail detail = new BlogDetail();
            detail.setThumbnail(dto.getThumbnail());
            detail.setDetailContent("Nội dung chi tiết mặc định hoặc để rỗng");
            detail.setBlog(blog);
            blog.setBlogDetail(detail);
        }

        Blog savedBlog = blogService.save(blog);

        // Trả về DTO có đầy đủ category name/id
        AdminBlogDTO res = new AdminBlogDTO();
        res.setBlogID(savedBlog.getBlogID());
        res.setTitle(savedBlog.getTitle());
        res.setContent(savedBlog.getContent());
        res.setStatus(savedBlog.getStatus().toString());
        res.setAuthor(savedBlog.getAuthor());
        res.setCreatedAt(savedBlog.getCreatedAt() != null ? savedBlog.getCreatedAt().toString() : null);
        if (savedBlog.getBlogDetail() != null) {
            res.setThumbnail(savedBlog.getBlogDetail().getThumbnail());
        }
        if (savedBlog.getBlogCategory() != null) {
            res.setBlogCategoryName(savedBlog.getBlogCategory().getCategoryName());
            res.setBlogCategoryID(savedBlog.getBlogCategory().getBlogCategoryId());
        }
        return ResponseEntity.ok(res);
    }


}


