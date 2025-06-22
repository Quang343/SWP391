package com.example.AgriculturalWarehouseManagement.Backend.controllers.Blogs;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import com.example.AgriculturalWarehouseManagement.Backend.models.Blog;
import com.example.AgriculturalWarehouseManagement.Backend.services.blogs.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RestController
public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping("/blog-list")
    public String bloglist(@RequestParam(defaultValue = "0") int page, // Số trang hiện tại (bắt đầu từ 0)
                           @RequestParam(defaultValue = "3") int size, // Số bài viết mỗi trang
                           Model model) {
        // Lấy Page<Blog> với phân trang và sắp xếp
        Page<Blog> blogPage = blogService.getActiveBlogsPage(page, size);
        // Lấy danh sách bài viết ở trang hiện tại
        model.addAttribute("blogs", blogPage.getContent());
        // Tổng số trang (phục vụ cho phân trang ở view)
        model.addAttribute("totalPages", blogPage.getTotalPages());
        // Số trang hiện tại
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);

        // Lấy 4 bài viết mới nhất cho sidebar/recent posts
        model.addAttribute("recentBlogs", blogService.getRecentBlogs(4));
        return "FrontEnd/Home/blog-list";
    }

    @RequestMapping("/blog-detail")
    public String blogdetail(@RequestParam("id") Integer id, Model model) {

        Blog blog = blogService.getBlogById(id);

        if (blog == null) {
            // Nếu không có blog, trả về trang 404 hoặc báo lỗi
            return "error/404";
        }
        model.addAttribute("blog", blog);

        // Lấy 4 bài viết mới nhất cho sidebar/recent posts
        model.addAttribute("recentBlogs", blogService.getRecentBlogs(4));

        return "FrontEnd/Home/blog-detail";
    }

    @RequestMapping("/blog-grid")
    public String bloggrid(@RequestParam(defaultValue = "0") int page, // Số trang hiện tại (bắt đầu từ 0)
                           @RequestParam(defaultValue = "3") int size, // Số bài viết mỗi trang
                           Model model) {
        // Lấy Page<Blog> với phân trang và sắp xếp
        Page<Blog> blogPage = blogService.getActiveBlogsPage(page, size);
        // Lấy danh sách bài viết ở trang hiện tại
        model.addAttribute("blogs", blogPage.getContent());
        // Tổng số trang (phục vụ cho phân trang ở view)
        model.addAttribute("totalPages", blogPage.getTotalPages());
        // Số trang hiện tại
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size); // Truyền biến size sang view để dùng trong phân trang

        // Lấy 4 bài viết mới nhất cho sidebar/recent posts
        model.addAttribute("recentBlogs", blogService.getRecentBlogs(4));
        return "FrontEnd/Home/blog-grid";
    }
}
