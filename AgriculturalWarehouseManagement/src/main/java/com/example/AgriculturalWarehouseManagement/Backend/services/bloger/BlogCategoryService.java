package com.example.AgriculturalWarehouseManagement.Backend.services.bloger;

import com.example.AgriculturalWarehouseManagement.Backend.models.BlogCategory;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.bloger.BlogCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//@author: Đào Huy Hoàng

@Service
@RequiredArgsConstructor
public class BlogCategoryService implements IBlogCategoryService {

    private final BlogCategoryRepository blogCategoryRepository;

    @Override
    public BlogCategory findById(Integer id) {
        return blogCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<BlogCategory> findAll() {
        return blogCategoryRepository.findAll();
    }

    // có thể thêm các method create, update, delete nếu cần
}
