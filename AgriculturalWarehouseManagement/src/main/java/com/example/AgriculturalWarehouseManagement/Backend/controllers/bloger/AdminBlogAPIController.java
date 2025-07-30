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
import org.springframework.http.HttpStatus;
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

//@author: Đào Huy Hoàng

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
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String status // <-- THÊM DÒNG NÀY
    ) {
        Page<Blog> blogPage;

        // Nếu có status và không phải "ALL", thì filter
        if (status != null && !status.equalsIgnoreCase("ALL") && !status.isEmpty()) {
            BlogStatus filterStatus;
            try {
                filterStatus = BlogStatus.valueOf(status.trim().toUpperCase());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Trạng thái không hợp lệ: " + status);
            }
            blogPage = blogService.getAllBlogsPageByStatus(page - 1, size, filterStatus); // bạn sẽ viết hàm này!
        } else {
            blogPage = blogService.getAllBlogsPage(page - 1, size);
        }

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
            } else if (blog.getBlogCategoryID() != null) {
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

    @PutMapping("/edit_blog/{id}")
    public ResponseEntity<?> editBlog(@PathVariable Integer id, @RequestBody AdminBlogDTO dto) {
        Blog blog = blogService.getBlogById(id);
        if (blog == null) {
            return ResponseEntity.badRequest().body("Blog không tồn tại");
        }

        // Cập nhật tiêu đề, nội dung, tác giả
        blog.setTitle(dto.getTitle());
        blog.setContent(dto.getContent());
        blog.setAuthor(dto.getAuthor());

        // Cập nhật trạng thái với kiểm tra lỗi
        try {
            blog.setStatus(BlogStatus.valueOf(dto.getStatus()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Trạng thái không hợp lệ");
        }

        // Cập nhật danh mục nếu có
        if (dto.getBlogCategoryID() != null) {
            BlogCategory blogCategory = blogCategoryService.findById(dto.getBlogCategoryID());
            if (blogCategory != null) {
                blog.setBlogCategory(blogCategory);
            } else {
                return ResponseEntity.badRequest().body("Danh mục không tồn tại");
            }
        }

        // Cập nhật thumbnail nếu có
        if (dto.getThumbnail() != null && !dto.getThumbnail().isEmpty()) {
            if (blog.getBlogDetail() != null) {
                blog.getBlogDetail().setThumbnail(dto.getThumbnail());
            }
        }

        // Cập nhật thời gian sửa đổi
        blog.setBlogDateUpdate(new Date());

        // Lưu blog đã cập nhật
        Blog updated = blogService.save(blog);

        // Chuyển sang DTO trước khi trả về
        AdminBlogDTO res = new AdminBlogDTO();
        res.setBlogID(updated.getBlogID());
        res.setTitle(updated.getTitle());
        res.setContent(updated.getContent());
        res.setStatus(updated.getStatus().toString());
        res.setAuthor(updated.getAuthor());
        res.setCreatedAt(updated.getCreatedAt() != null ? updated.getCreatedAt().toString() : null);
        res.setBlogCategoryID(updated.getBlogCategoryID());
        if (updated.getBlogDetail() != null) {
            res.setThumbnail(updated.getBlogDetail().getThumbnail());
        }

        return ResponseEntity.ok(res);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getBlogDetail(@PathVariable Integer id) {
        Blog blog = blogService.getBlogById(id);
        if (blog == null) return ResponseEntity.notFound().build();

        // Chuyển đổi blog thành DTO
        AdminBlogDTO dto = new AdminBlogDTO();
        dto.setBlogID(blog.getBlogID());
        dto.setTitle(blog.getTitle());
        dto.setContent(blog.getContent());
        dto.setStatus(blog.getStatus().toString());
        dto.setAuthor(blog.getAuthor());
        dto.setCreatedAt(blog.getCreatedAt() != null ? blog.getCreatedAt().toString() : null);

        // Nếu có blogDetail thì thêm thumbnail vào DTO
        if (blog.getBlogDetail() != null) {
            dto.setThumbnail(blog.getBlogDetail().getThumbnail());
        }

        // Cũng có thể set blogCategoryID nếu cần thiết
        dto.setBlogCategoryID(blog.getBlogCategoryID());

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/delete_blog/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Integer id) {
        Blog blog = blogService.getBlogById(id);
        if (blog == null) {
            return ResponseEntity.badRequest().body("Blog không tồn tại");
        }

        // Chuyển trạng thái của blog thành DELETED
        blog.setStatus(BlogStatus.DELETED);

        // Cập nhật blog trong cơ sở dữ liệu
        blog.setBlogDateUpdate(new Date());
        blogService.save(blog);

        return ResponseEntity.ok("Blog đã được chuyển sang trạng thái DELETED");
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBlogs(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {

        Page<Blog> blogPage = blogService.searchBlogs(keyword, page - 1, size);
        List<AdminBlogDTO> blogDTOs = blogPage.getContent().stream().map(blog -> {
            AdminBlogDTO dto = new AdminBlogDTO();
            dto.setBlogID(blog.getBlogID());
            dto.setTitle(blog.getTitle());
            dto.setContent(blog.getContent());
            dto.setStatus(blog.getStatus().toString());
            dto.setAuthor(blog.getAuthor());
            dto.setCreatedAt(blog.getCreatedAt() != null ? blog.getCreatedAt().toString() : null);
            dto.setThumbnail(blog.getBlogDetail() != null ? blog.getBlogDetail().getThumbnail() : null);
            dto.setBlogCategoryName(blog.getBlogCategory() != null ? blog.getBlogCategory().getCategoryName() : "");
            return dto;
        }).toList();

        Map<String, Object> result = new HashMap<>();
        result.put("blogs", blogDTOs);
        result.put("totalPages", blogPage.getTotalPages());
        result.put("currentPage", blogPage.getNumber() + 1);

        return ResponseEntity.ok(result);
    }


}


