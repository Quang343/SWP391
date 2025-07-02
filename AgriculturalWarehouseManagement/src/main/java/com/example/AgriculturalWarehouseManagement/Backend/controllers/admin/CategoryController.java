package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.CategoryDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Category;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@RequiredArgsConstructor
@Controller
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/admin/categories")
    public String adminCategory(Model model,
                                @RequestParam("page") Optional<String> page) {
//        List<String> status = new ArrayList<>(Arrays.asList("ACTIVE", "INACTIVE"));
//        List<Category> categories = categoryService.findCategoryByStatusIn(status);
        int pageNumber = 1;
        try{
            if (page.isPresent()) {
                pageNumber = Integer.parseInt(page.get());
            }
            else{
                //pageNumber = 1
            }
        }catch (Exception e){
            //pageNumber = 1
            //Handle exception
        }
        Pageable pageable = PageRequest.of(pageNumber - 1, 1);
        Page<Category> categoryPage = categoryService.findAll(pageable);
        List<Category> categories = categoryPage.getContent();
        model.addAttribute("categories", categories);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", categoryPage.getTotalPages());
        return "BackEnd/Admin/Category";
    }

    @GetMapping("/admin/add_category")
    public String showCreateForm(Model model) {
        model.addAttribute("categoryDTO", new CategoryDTO());
        return "BackEnd/Admin/Add_Category";
    }

    @GetMapping("/admin/edit_category/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) throws Exception {
        Category category = categoryService.findById(id);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setStatus(category.getStatus());
        categoryDTO.setImageUrl(category.getImageUrl());
        model.addAttribute("categoryDTO", categoryDTO);
        model.addAttribute("id", category.getId());
        return "BackEnd/Admin/Edit_Category";
    }

    @PostMapping("/admin/update_category/{id}")
    public ResponseEntity<?> updateCategory(
            @PathVariable("id") Long id,
            @RequestParam("name") String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("status") String status,
            @RequestPart(value = "image", required = false) MultipartFile image
    ){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(name);
        categoryDTO.setDescription(description);
        categoryDTO.setStatus(status);

        try {
            Category existingCategory = categoryService.findById(id);
            if (image != null && !image.isEmpty()) {
                String oldFileName = existingCategory.getImageUrl();
                if(oldFileName != null && !oldFileName.isBlank()){
                    deleteFile(oldFileName);
                }

                String newFileName = storeFile(image);
                categoryDTO.setImageUrl(newFileName);
            }else
                categoryDTO.setImageUrl(existingCategory.getImageUrl());

            categoryService.updateCategory(id, categoryDTO);
            return ResponseEntity.ok().body("Category updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public void deleteFile(String fileName){
        try{
            Path filePath = Paths.get(uploadDir, "Admin").resolve(fileName);
            Files.deleteIfExists(filePath);
        }catch (Exception e){
            System.err.println("Failed to delete old file: " + e.getMessage());
        }
    }

    @PostMapping("/admin/saveCategory")
    public ResponseEntity<?> saveCategory(
            @RequestParam("name") String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("status") String status,
            @RequestPart(value = "image", required = false) MultipartFile image
    ){
        if(categoryService.existsByNameIgnoreCase(name)) {
            return ResponseEntity.badRequest().body("Category name already exists");
        }

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(name);
        categoryDTO.setDescription(description);
        categoryDTO.setStatus(status);

        if (image != null && !image.isEmpty()) {
            try {
                String fileName = storeFile(image);
                categoryDTO.setImageUrl(fileName);
            }catch (Exception e){
                return ResponseEntity.badRequest().body("Image upload failed");
            }
        }
        categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok(Map.of("message", "Category created successfully"));
    }

    @Value("${app.upload.product-dir}")
    private String uploadDir;

    private String storeFile(MultipartFile file) throws IOException {
        if (file.getOriginalFilename() == null) {
            throw new IOException("Empty file name");
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        //Thêm UUID vào trước để đảm bảo fileName là duy nhất
        String uniqueFileName = UUID.randomUUID().toString() + "." + fileName;
        //Đường dẫn đến thư mục mà bạn muốn lưu file
//        java.nio.file.Path uploadDir = Paths.get("src/main/resources/static/Backend/assets/images/category");
        //Kiểm tra và tạo nếu thư mục chưa tồn tại
        Path uploadPath = Paths.get(uploadDir, "Admin");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        //Đường dẫn đầy đủ đến file
        Path destination = uploadPath.resolve(uniqueFileName);
        //Sao chép file vào thư mục đích
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }

    @DeleteMapping("/admin/delete_category/{id}")
    public ResponseEntity<?> softDelete(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok().body(Map.of("message", "Category deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
