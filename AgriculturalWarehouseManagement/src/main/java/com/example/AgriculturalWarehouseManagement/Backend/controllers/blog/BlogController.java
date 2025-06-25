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
            Model model) {

        if (page < 1) page = 1; // Luôn đảm bảo page >= 1
        int pageIndex = page - 1; // Spring Data page index bắt đầu từ 0

        // Lấy Page<Blog> với phân trang và sắp xếp
        Page<Blog> blogPage = blogService.getActiveBlogsPage(pageIndex, size);

        model.addAttribute("blogs", blogPage.getContent());
        model.addAttribute("totalPages", blogPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("recentBlogs", blogService.getRecentBlogs(4));
        return "FrontEnd/Home/blog-list";
    }

    @RequestMapping("/blog-detail")
    public String blogdetail(@RequestParam("id") Integer id, Model model) {


        Blog blog = blogService.getBlogById(id);

        if (blog == null) {
            // Không có blog, trả về trang 404 hoặc báo lỗi
            return "error/404";
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

        model.addAttribute("blogs", blogPage.getContent());
        model.addAttribute("totalPages", blogPage.getTotalPages());
        model.addAttribute("currentPage", page); // Truyền page (bắt đầu từ 1)
        model.addAttribute("size", size);
        model.addAttribute("recentBlogs", blogService.getRecentBlogs(4));
        return "FrontEnd/Home/blog-grid";
    }

}
