package com.example.AgriculturalWarehouseManagement.Backend;

import com.example.AgriculturalWarehouseManagement.dtos.ProductDTO;
import com.example.AgriculturalWarehouseManagement.models.Category;
import com.example.AgriculturalWarehouseManagement.models.Product;
import com.example.AgriculturalWarehouseManagement.models.ProductStatus;
import com.example.AgriculturalWarehouseManagement.services.CategoryService;
import com.example.AgriculturalWarehouseManagement.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/seller-dashboard")
public class SellerController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;


    @GetMapping("/add_product")
    public String showCreateForm(Model model) {
        List<Category> categories = categoryService.findAll();
        List<String> statuses = getProductStatus();
        model.addAttribute("statuses", statuses);
        model.addAttribute("categories", categories);
        model.addAttribute("productDTO", new ProductDTO());
        return "FrontEnd/Seller/seller-dashboard";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("productDTO") @Valid ProductDTO productDTO,
                              BindingResult bindingResult,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "FrontEnd/Seller/seller-dashboard";
        }
        if (productService.existsByName(productDTO.getName())) {
            bindingResult.rejectValue("name", "error.name", "Product name already exists");
            List<Category> categories = categoryService.findAll();
            List<String> statuses = getProductStatus();
            model.addAttribute("statuses", statuses);
            model.addAttribute("categories", categories);
            return "FrontEnd/Seller/seller-dashboard";
        }
        try {
            productService.createProduct(productDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Product has been created");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add the product");
        }
        return "redirect:/seller-dashboard/add_product";
    }

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "BackEnd/Seller/All_Products";
    }

    private List<String> getProductStatus() {
        return Arrays.stream(ProductStatus.values())
                .map(Enum::name)
                .toList();
    }

    private void config() {
        modelMapper.typeMap(Product.class, ProductDTO.class);
    }

    @GetMapping("/edit_product/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.findById(id);
        config();
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        List<String> statuses = getProductStatus();
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("statuses", statuses);
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("id", id);
        return "BackEnd/Seller/Edit_Product";
    }

    @PutMapping("/update_product/{id}")
    public String updateProduct(@PathVariable("id") Long id,
                                @ModelAttribute @Valid ProductDTO productDTO,
                                RedirectAttributes redirectAttributes,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "BackEnd/Seller/Edit_Product";
        }
        Product product = productService.findById(id);
        if (!product.getName().equals(productDTO.getName()) &&
                productService.existsByName(productDTO.getName())) {
            bindingResult.rejectValue("name", "error.name", "Product name already exists");
            List<Category> categories = categoryService.findAll();
            List<String> statuses = getProductStatus();
            model.addAttribute("statuses", statuses);
            model.addAttribute("categories", categories);
            return "BackEnd/Seller/Edit_Product";
        }
        try {
            productService.updateProduct(id, productDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Product has been updated");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update the product");
        }
        return "redirect:/seller/products";
    }

    @DeleteMapping("/delete_product/{id}")
    public String deleteProduct(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted)
            redirectAttributes.addFlashAttribute("successMessage", "Product has been deleted");
        else
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete the product");
        return "redirect:/seller/products";
    }
}
