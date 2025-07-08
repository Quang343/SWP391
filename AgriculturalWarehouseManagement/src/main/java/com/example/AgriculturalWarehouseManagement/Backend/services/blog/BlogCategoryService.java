package com.example.AgriculturalWarehouseManagement.Backend.services.blog;

import com.example.AgriculturalWarehouseManagement.Backend.models.Blog;
import com.example.AgriculturalWarehouseManagement.Backend.models.BlogCategory;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.blog.BlogCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogCategoryService implements IBlogCategoryService {

    private final BlogCategoryRepository blogCategoryRepository;

    @Override
    public BlogCategory findById(Long id) {
        return blogCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<BlogCategory> findAll() {
        return blogCategoryRepository.findAll();
    }

    // có thể thêm các method create, update, delete nếu cần
}
