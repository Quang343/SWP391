package com.example.AgriculturalWarehouseManagement.Backend.controllers.Blogs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import com.example.AgriculturalWarehouseManagement.Backend.models.Blog;
import com.example.AgriculturalWarehouseManagement.Backend.services.blogs.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
//@RestController
public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // Hiển thị danh sách blog động
    @RequestMapping("/blog-list")
    public String bloglist(Model model) {
        List<Blog> blogs = blogService.getAllActiveBlogs();  // Lấy danh sách blog (trạng thái Active)
      model.addAttribute("blogs", blogs);
      model.addAttribute("recentBlogs", blogService.getRecentBlogs(4)); // lấy 4 bài mới nhất

        return "FrontEnd/Home/blog-list"; // Trả về giao diện blog-list
    }

//    @GetMapping("/blog-list")
//    public ResponseEntity<?> bloglist() {
//        List<Blog> blogs = blogService.findAll();
//
//        for (Blog blog : blogs) {
//            System.out.printf("hello 1"+blog.toString());
//        }
//        return ResponseEntity.ok(blogs);
//    }

    @RequestMapping("/blog-detail")
    public String blogdetail() {
        return "FrontEnd/Home/blog-detail";
    }

    @RequestMapping("/blog-grid")
    public String bloggrid() {
        return "FrontEnd/Home/blog-grid";
    }
}
