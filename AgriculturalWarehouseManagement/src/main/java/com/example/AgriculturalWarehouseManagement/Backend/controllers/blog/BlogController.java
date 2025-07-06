package com.example.AgriculturalWarehouseManagement.Backend.controllers.blog;

import com.example.AgriculturalWarehouseManagement.Backend.models.Blog;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.UserService;
import com.example.AgriculturalWarehouseManagement.Backend.services.blog.BlogService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final UserService userService; // Để lấy thông tin user

//    @Autowired
//    public BlogController(BlogService blogService, UserService userService) {
//        this.blogService = blogService;
//        this.userService = userService;
//    }

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

    // CRUD

    @RequestMapping("/my-blog")
    public String myBlog(Model model) {
        // User user = userService.findByUsername("daohuyhoang507@gmail.com"); // hoặc username/email test
        User user = userService.findById(1L); // lấy user id = 1
        List<Blog> myBlogs = blogService.getBlogsByUser(user.getUserId());
        model.addAttribute("blogs", myBlogs);
        model.addAttribute("user", user);
        return "FrontEnd/Home/my-blog";
    }

    // Lấy thông tin blog của user / Cách 1
//    @RequestMapping("/my-blog")
//    public String myBlog(Model model, Principal principal) {
//        if (principal == null) {
//            return "redirect:/login"; // Chưa login thì cho về login
//        }
//
//        String username = principal.getName(); // Lấy username từ phiên đăng nhập
//        User user = userService.findByUsername(username); // Lấy user từ DB
//
//        if (user == null) {
//            return "FrontEnd/Home/error_404"; // Nếu user không tồn tại
//        }
//
//        List<Blog> myBlogs = blogService.getBlogsByUser(user.getUserId()); // Lấy blog theo user
//        model.addAttribute("blogs", myBlogs);
//        model.addAttribute("user", user);
//
//        return "FrontEnd/Home/my-blog";
//    }

    // Cách 2: Giúp các chỗ khác không cần gọi lại findByUsername nữa. Tiện cho các request sau!
//    @RequestMapping("/my-blog")
//    public String myBlog(Model model, Principal principal, HttpSession session) {
//        if (principal == null) {
//            return "redirect:/login";
//        }
//
//        // Kiểm tra trong session đã có user chưa, chưa có thì lấy từ DB và lưu lại
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            String username = principal.getName();
//            user = userService.findByUsername(username);
//            if (user == null) {
//                return "FrontEnd/Home/error_404";
//            }
//            session.setAttribute("user", user); // Lưu vào session để lần sau dùng
//        }
//
//        List<Blog> myBlogs = blogService.getBlogsByUser(user.getUserId());
//        model.addAttribute("blogs", myBlogs);
//        model.addAttribute("user", user);
//
//        return "FrontEnd/Home/my-blog";
//    }
}
