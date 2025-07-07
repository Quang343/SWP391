package com.example.AgriculturalWarehouseManagement.Backend.controllers.blog;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.blog.BlogDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Blog;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.UserService;
import com.example.AgriculturalWarehouseManagement.Backend.services.blog.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/my-blog")
@RequiredArgsConstructor
public class BlogRestController {

    private final BlogService blogService;
    private final UserService userService;

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
        Page<Blog> blogPage = blogService.getBlogsByUserPage(user.getUserId(), page - 1, size);

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


}
