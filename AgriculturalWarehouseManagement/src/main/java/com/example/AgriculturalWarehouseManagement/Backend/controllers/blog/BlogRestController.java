package com.example.AgriculturalWarehouseManagement.Backend.controllers.blog;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.blog.BlogDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Blog;
import com.example.AgriculturalWarehouseManagement.Backend.models.BlogCategory;
import com.example.AgriculturalWarehouseManagement.Backend.models.BlogStatus;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.UserService;
import com.example.AgriculturalWarehouseManagement.Backend.services.blog.BlogCategoryService;
import com.example.AgriculturalWarehouseManagement.Backend.services.blog.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.*;

//@author: Đào Huy Hoàng

@RestController
@RequestMapping("/api/my-blog")
@RequiredArgsConstructor
public class BlogRestController {

    private final BlogService blogService;
    private final UserService userService;
    private final BlogCategoryService blogCategoryService;


    @Value("${app.upload.product-dir}")
    private String uploadDir;

    @GetMapping("/page")
    public ResponseEntity<?> getMyBlogPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size
            //   Principal principal // Thêm tham số này
    ) {
        User user = userService.findById(1L);

        // Nếu có tích hợp security thì lấy user từ Principal, không thì fallback về user test
//        if (principal != null) {
//            String username = principal.getName();
//            user = userService.findByUsername(username);
//        } else {
//            // Chưa tích hợp security thì tạm lấy user test id=1
//            user = userService.findById(1L);
//        }

        // ACTIVE
        //  Page<Blog> blogPage = blogService.getBlogsByUserPage(user.getUserId(), page - 1, size);

        Page<Blog> blogPage = blogService.getAllStatusBlogsByUserPage(user.getUserId(), page - 1, size);

        List<BlogDTO> blogDTOs = blogPage.getContent().stream().map(blog -> {
            BlogDTO dto = new BlogDTO();
            dto.setBlogID(blog.getBlogID());
            dto.setTitle(blog.getTitle());
            dto.setContent(blog.getContent());
            dto.setStatus(blog.getStatus().toString());
            dto.setAuthor(blog.getAuthor());
            dto.setCreatedAt(blog.getCreatedAt() != null ? blog.getCreatedAt().toString() : null);
            if (blog.getBlogDetail() != null) {
                dto.setThumbnail(blog.getBlogDetail().getThumbnail());
            }
            return dto;
        }).toList();

        Map<String, Object> result = new HashMap<>();
        result.put("blogs", blogDTOs);
        result.put("totalPages", blogPage.getTotalPages());
        result.put("currentPage", blogPage.getNumber() + 1);

        return ResponseEntity.ok(result);
    }


    // Thêm blog (thumbnail là tên file đã upload trước, truyền kèm lên từ FE)
    @PostMapping("/add_blog")
    public ResponseEntity<?> addBlog(@RequestBody Blog blog) {
        blog.setCreatedAt(new Date());
        blog.setStatus(BlogStatus.DRAFT); // Mặc định là DRAFT
        // Xử lý thêm các trường, validate nếu cần
        if (blog.getBlogDetail() != null) {
            blog.getBlogDetail().setBlog(blog); // set lại quan hệ
        }
        Blog savedBlog = blogService.save(blog);
        return ResponseEntity.ok(savedBlog);
    }

    // Upload ảnh thumbnail
    @PostMapping("/upload-thumbnail")
    public ResponseEntity<?> uploadThumbnail(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded!");
        }
        try {
            String fileName = saveThumbnailFile(file);
            // FE chỉ cần lưu fileName này, lúc render <img src="/blog/{fileName}">
            return ResponseEntity.ok(fileName);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload error: " + e.getMessage());
        }
    }

    // Hàm lưu file vào thư mục đúng config
    private String saveThumbnailFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        // Lưu đúng thư mục Blog, dùng config!
        Path uploadPath = Paths.get(uploadDir, "Blog");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        Files.copy(file.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        return fileName;
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

    @PutMapping("/edit_blog/{id}")
    public ResponseEntity<?> editBlog(@PathVariable Integer id, @RequestBody Blog blogUpdate) {
        Blog blog = blogService.getBlogById(id);
        if (blog == null) {
            return ResponseEntity.badRequest().body("Blog không tồn tại");
        }
        // Update các trường (tuỳ yêu cầu)
        blog.setTitle(blogUpdate.getTitle());
        blog.setContent(blogUpdate.getContent());
        blog.setStatus(blogUpdate.getStatus());
        blog.setAuthor(blogUpdate.getAuthor());
        blog.setBlogCategoryID(blogUpdate.getBlogCategoryID());
        blog.setBlogDateUpdate(new Date());
        // Nếu cập nhật ảnh
        if (blogUpdate.getBlogDetail() != null && blog.getBlogDetail() != null) {
            blog.getBlogDetail().setThumbnail(blogUpdate.getBlogDetail().getThumbnail());
        }
        Blog updated = blogService.save(blog);
        return ResponseEntity.ok(updated);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getBlogDetail(@PathVariable Integer id) {
        Blog blog = blogService.getBlogById(id);
        if (blog == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(blog);
    }
    @DeleteMapping("/delete_blog/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Integer id) {
        blogService.deleteById(id);
        return ResponseEntity.ok("Xoá thành công");
    }
}
