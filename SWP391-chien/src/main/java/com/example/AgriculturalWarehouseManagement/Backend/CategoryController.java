package com.example.AgriculturalWarehouseManagement.Backend;

import com.example.AgriculturalWarehouseManagement.dtos.CategoryDTO;
import com.example.AgriculturalWarehouseManagement.models.Category;
import com.example.AgriculturalWarehouseManagement.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/admin/categories")
    public String adminCategory(Model model) {
        List<String> status = new ArrayList<>(Arrays.asList("ACTIVE", "INACTIVE"));
        List<Category> categories = categoryService.findCategoryByStatusIn(status);
        model.addAttribute("categories", categories);
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
        model.addAttribute("categoryDTO", categoryDTO);
        model.addAttribute("id", category.getId());
        return  "BackEnd/Admin/Edit_Category";
    }

    @PutMapping("/admin/update_category/{id}")
    public String updateCategory(@PathVariable Long id,
                                 @ModelAttribute CategoryDTO categoryDTO,
                                 RedirectAttributes redirectAttributes){
        try {
            Category category = categoryService.updateCategory(id, categoryDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Updated Successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Updated error: " + e.getMessage());
        }
        return "redirect:/admin/categories";
    }

    @PostMapping("/admin/saveCategory")
    public String saveCategory(@ModelAttribute @Valid CategoryDTO categoryDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "BackEnd/Admin/Add_Category";
        }
        if (categoryService.existsByName(categoryDTO)) {
            bindingResult.rejectValue("name", "error.name", "Category name already exists");
            return "BackEnd/Admin/Add_Category";
        }
        try{
            Category category = categoryService.createCategory(categoryDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Category added successfully!");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add category: " + e.getMessage());
        }
        return "redirect:/admin/categories";
    }

    @DeleteMapping("/admin/delete_category/{id}")
    public String softDelete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        try{
            categoryService.deleteCategory(id);
            redirectAttributes.addFlashAttribute("successMessage", "Category deleted successfully!");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete category with error: " + e.getMessage());
        }
        return "redirect:/admin/categories";
    }

}
