package com.example.AgriculturalWarehouseManagement.Backend.controllers.blog;

import com.example.AgriculturalWarehouseManagement.Backend.models.Blog;
import com.example.AgriculturalWarehouseManagement.Backend.services.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RestController
public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping("/blog-list")
    public String bloglist(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(required = false) String keyword,
            Model model) {

        if (page < 1) page = 1;
        int pageIndex = page - 1;

        Page<Blog> blogPage;

        // Nếu có keyword thì search, không thì lấy tất cả
        if (keyword != null && !keyword.trim().isEmpty()) {
            blogPage = blogService.searchBlogs(keyword, pageIndex, size);
        } else {
            blogPage = blogService.getActiveBlogsPage(pageIndex, size);
        }

        int totalPages = blogPage.getTotalPages();
        int currentPage = page;
        int maxPages = 5; // Số nút trang muốn hiển thị

        // Tính toán sliding window cho phân trang
        int startPage = Math.max(1, currentPage - maxPages / 2);
        int endPage = Math.min(totalPages, startPage + maxPages - 1);
        startPage = Math.max(1, endPage - maxPages + 1);

        model.addAttribute("blogs", blogPage.getContent());
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("size", size);
        model.addAttribute("keyword", keyword); // Giữ lại giá trị search khi reload
        model.addAttribute("recentBlogs", blogService.getRecentBlogs(4));

        // Thêm biến cho phân trang
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("maxPages", maxPages);

        return "FrontEnd/Home/blog-list";
    }



    @RequestMapping("/blog-detail")
    public String blogdetail(@RequestParam(value = "id", required = false) Integer id, Model model) {

        if (id == null) {
            return "redirect:/blog-list";
        }

        Blog blog = blogService.getBlogById(id);

        if (blog == null || id <= 0) {
            return "FrontEnd/Home/error_404";
        }
        model.addAttribute("blog", blog);

        // Lấy 4 bài viết mới nhất cho sidebar/recent posts
        model.addAttribute("recentBlogs", blogService.getRecentBlogs(4));

        // (Có thể lấy thêm comment nếu muốn)
        // List<CommentBlog> comments = commentBlogService.getByBlogId(id);
        // model.addAttribute("comments", comments);

        return "FrontEnd/Home/blog-detail"; // Đường dẫn file html đúng với cấu trúc project
    }


    @RequestMapping("/blog-grid")
    public String bloggrid(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size,
            Model model) {

        if (page < 1) page = 1; // Nếu user nhập page=0 hay page<1, set lại thành 1

        int pageIndex = page - 1; // Chuyển từ page=1 thành pageIndex=0
        Page<Blog> blogPage = blogService.getActiveBlogsPage(pageIndex, size);

        int totalPages = blogPage.getTotalPages();
        int currentPage = page;
        int maxPages = 5;

        int startPage = Math.max(1, currentPage - maxPages / 2);
        int endPage = Math.min(totalPages, startPage + maxPages - 1);
        startPage = Math.max(1, endPage - maxPages + 1);

        model.addAttribute("blogs", blogPage.getContent());
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("size", size);
        model.addAttribute("recentBlogs", blogService.getRecentBlogs(4));

        // Thêm biến phân trang cho giao diện
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("maxPages", maxPages);

        return "FrontEnd/Home/blog-grid";
    }


}
