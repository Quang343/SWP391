package com.example.AgriculturalWarehouseManagement.Backend.controllers.blog;

import com.example.AgriculturalWarehouseManagement.Backend.models.Blog;
import com.example.AgriculturalWarehouseManagement.Backend.services.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

//@RestController
//public class BlogRestController {
//
//    @RequestMapping(path = {"blog/my-blog" , "/api/my-blog"})
//    public String myblog(){
//        return "FrontEnd/Home/my-blog";
//    }
//}

@RestController
@RequestMapping("/api/blogs")
public class BlogRestController {
//    @Autowired
//    private BlogService blogService;
//
//    @GetMapping("/user/{userId}")
//    public Page<Blog> getBlogsByUser(@PathVariable Long userId,
//                                     @RequestParam(defaultValue = "0") int page,
//                                     @RequestParam(defaultValue = "10") int size) {
//        return blogService.getBlogsByUser(userId, page, size);
//    }
}

