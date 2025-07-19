package com.example.AgriculturalWarehouseManagement.Backend.services.bloger;

import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.blog.UserBlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//@author: Đào Huy Hoàng

@Service
@RequiredArgsConstructor
public class UserServiceBlogImpl implements UserServiceBlog {

    private final UserBlogRepository userBlogRepository;

    @Override
    public User findById(Integer id) {
        return userBlogRepository.findById(id).orElse(null);
    }
}
