package com.example.AgriculturalWarehouseManagement.Backend.controllers.bloger;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ProductDetailUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.models.Blog;
import com.example.AgriculturalWarehouseManagement.Backend.models.CommentBlog;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.bloger.BlogService;
import com.example.AgriculturalWarehouseManagement.Backend.services.bloger.CommentBlogService;
import com.example.AgriculturalWarehouseManagement.Backend.services.bloger.UserServiceBlog;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.ProductDetailUserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


//@author: Đào Huy Hoàng

@Controller
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final CommentBlogService commentBlogService;
    private final UserServiceBlog userServiceBlog; // Cap nhat them
    private final HttpSession session;
    private final ProductDetailUserService productDetailUserService;

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

        // Recent posts
        model.addAttribute("recentBlogs", blogService.getRecentBlogs(4));

        // Load Comments
        model.addAttribute("comments", commentBlogService.getCommentsByBlogId(id));

        return "FrontEnd/Home/blog-detail";
    }

    @PostMapping("/blog-detail/comment")
    public String postComment(@RequestParam("blogId") Integer blogId,
                              @RequestParam("commentText") String commentText,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        try {
            Object sessionAccount = session.getAttribute("account");

            if (sessionAccount instanceof UserResponse userResponse) {
                User user = userServiceBlog.findById(userResponse.getUserID());

                if (user == null) {
                    redirectAttributes.addFlashAttribute("error", "Không tìm thấy người dùng.");
                    return "redirect:/login";
                }

                Blog blog = blogService.getBlogById(blogId);
                if (blog == null) {
                    redirectAttributes.addFlashAttribute("error", "Không tìm thấy blog.");
                    return "redirect:/blog-list";
                }

                CommentBlog comment = CommentBlog.builder()
                        .blog(blog)
                        .user(user)
                        .commentText(commentText)
                        .status("Active")
                        .build();

                commentBlogService.save(comment);
                return "redirect:/blog-detail?id=" + blogId;
            }

            redirectAttributes.addFlashAttribute("error", "Bạn cần đăng nhập để bình luận.");
            return "redirect:/login";

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Đã xảy ra lỗi khi đăng bình luận: " + e.getMessage());
            return "redirect:/blog-detail?id=" + blogId;
        }
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

        // Trending Products
        List<ProductDetailUserResponse> trendingProducts = productDetailUserService.getTrendingProducts();
        model.addAttribute("trendingProducts", trendingProducts);

        return "FrontEnd/Home/blog-list";
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

    @RequestMapping("/my-blog")
    public String myBlog() {
        Object sessionAccount = session.getAttribute("account");
        if (sessionAccount == null) {
            return "redirect:/home";
        }
        return "FrontEnd/Home/my-blog";
    }

    @RequestMapping("/admin/blog")
    public String adminBlog() {
        System.out.println("Vào tới controller adminBlog");
        return "BackEnd/Blog/All_Blog";
    }

    @RequestMapping("/admin/add_blog")
    public String adminAddBlog() {
        return "BackEnd/Blog/Add_Blog";
    }
}
