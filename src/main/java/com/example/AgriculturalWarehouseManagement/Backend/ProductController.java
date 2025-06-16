package com.example.AgriculturalWarehouseManagement.Backend;

import com.example.AgriculturalWarehouseManagement.dtos.ProductDTO;
import com.example.AgriculturalWarehouseManagement.dtos.ProductImageDTO;
import com.example.AgriculturalWarehouseManagement.dtos.UserDTO;
import com.example.AgriculturalWarehouseManagement.models.Category;
import com.example.AgriculturalWarehouseManagement.models.Product;
import com.example.AgriculturalWarehouseManagement.models.ProductImage;
import com.example.AgriculturalWarehouseManagement.models.ProductStatus;
import com.example.AgriculturalWarehouseManagement.services.CategoryService;
import com.example.AgriculturalWarehouseManagement.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Controller
//@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @GetMapping("/admin/add_product")
    public String showCreateForm(Model model){
        List<Category> categories = categoryService.findAll();
        List<String> statuses = getProductStatus();
        model.addAttribute("statuses", statuses);
        model.addAttribute("categories", categories);
        model.addAttribute("productDTO", new ProductDTO());
        return "BackEnd/Admin/Add_Product";
    }

    @PostMapping("/admin/saveProduct")
    public String saveProduct(@ModelAttribute("productDTO") @Valid ProductDTO productDTO,
                              BindingResult bindingResult,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            return "BackEnd/Admin/Add_Product";
        }
        if(productService.existsByName(productDTO.getName())){
            bindingResult.rejectValue("name", "error.name",  "Product name already exists");
            List<Category> categories = categoryService.findAll();
            List<String> statuses = getProductStatus();
            model.addAttribute("statuses", statuses);
            model.addAttribute("categories", categories);
            return "BackEnd/Admin/Add_Product";
        }
        try{
            Product product = productService.createProduct(productDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Product has been created");
        }
        catch(Exception e){
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add the product");
        }
        return "redirect:/admin/products";
    }

    private List<String> getProductStatus(){
        return Arrays.stream(ProductStatus.values())
                .map(Enum::name)
                .toList();
    }

    @GetMapping("/admin/products")
    public String getAllProducts(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "BackEnd/Admin/All_Products";
    }

    private void config(){
        modelMapper.typeMap(Product.class, ProductDTO.class);
    }


    @GetMapping("/admin/edit_product/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model){
        Product product = productService.findById(id);
        config();
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        List<String> statuses = getProductStatus();
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("statuses", statuses);
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("id", id);
        return "/BackEnd/Admin/Edit_Product";
    }


//    @PutMapping("/admin/update_product/{id}")
//    public String updateProduct(@PathVariable("id") Long id,
//                                @ModelAttribute @Valid ProductDTO productDTO,
//                                RedirectAttributes redirectAttributes,
//                                BindingResult bindingResult,
//                                Model model
//    ){
//        if(bindingResult.hasErrors()){
//            return "BackEnd/Admin/Edit_Product";
//        }
//        Product product = productService.findById(id);
//        if(!product.getName().equals(productDTO.getName()) &&
//                productService.existsByName(productDTO.getName())){
//            bindingResult.rejectValue("name", "error.name",  "Product name already exists");
//            List<Category> categories = categoryService.findAll();
//            List<String> statuses = getProductStatus();
//            model.addAttribute("statuses", statuses);
//            model.addAttribute("categories", categories);
//            return "BackEnd/Admin/Edit_Product";
//        }
//        try{
//            productService.updateProduct(id, productDTO);
//            redirectAttributes.addFlashAttribute("successMessage", "Product has been updated");
//        }
//        catch(Exception e){
//            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update the product");
//        }
//        return "redirect:/admin/products";
//    }

//    @PostMapping("/admin/update_product/{id}")
//    @ResponseBody
//    public ResponseEntity<?> updateProduct(
//            @PathVariable("id") Long id,
//            @ModelAttribute @Valid ProductDTO productDTO,
//            BindingResult bindingResult
//    ) {
//        if (bindingResult.hasErrors()) {
//            // Trả về lỗi validation (dạng JSON)
//            Map<String, String> errors = new HashMap<>();
//            bindingResult.getFieldErrors().forEach(error ->
//                    errors.put(error.getField(), error.getDefaultMessage()));
//            return ResponseEntity.badRequest().body(errors);
//        }
//
//        Product product = productService.findById(id);
//        if (product == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Product not found");
//        }
//
//        // Kiểm tra trùng tên
//        if (!product.getName().equals(productDTO.getName().trim()) &&
//                productService.existsByName(productDTO.getName())) {
//            return ResponseEntity.badRequest().body(
//                    Map.of("name", "Product name already exists")
//            );
//        }
//
//        try {
//            productService.updateProduct(id, productDTO);
//            return ResponseEntity.ok("Product updated successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to update the product");
//        }
//    }


    @DeleteMapping("/admin/delete_product/{id}")
    public String deleteProduct(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        boolean isDeleted = productService.deleteProduct(id);
        if(isDeleted)
            redirectAttributes.addFlashAttribute("successMessage", "Product has been deleted");
        else
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete the product");
        return "redirect:/admin/products";
    }


//    @GetMapping("/admin/products")
//    public ResponseEntity<?> getAllProducts(){
//        List<Product> products = productService.findAll();
//        return ResponseEntity.ok(products);
//    }

    @PostMapping(value = "/admin/update_product/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProductWithImages(
            @PathVariable Long id,
            @Valid @ModelAttribute ProductDTO productDTO,
            BindingResult bindingResult,
            @RequestParam(value = "files", required = false) List<MultipartFile> images
            ) {
        try {
            // 1. Cập nhật dữ liệu sản phẩm
            if (bindingResult.hasErrors()) {
            // Trả về lỗi validation (dạng JSON)
                Map<String, String> errors = new HashMap<>();
                bindingResult.getFieldErrors().forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage()));
                return ResponseEntity.badRequest().body(errors);
            }

            Product product = productService.findById(id);
            if (product == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Product not found");
            }

            // Kiểm tra trùng tên
            if (!product.getName().equals(productDTO.getName().trim()) &&
                    productService.existsByName(productDTO.getName())) {
                return ResponseEntity.badRequest().body(
                        Map.of("name", "Product name already exists")
                );
            }
            Product updatedProduct = productService.updateProduct(id, productDTO);

            // 2. Nếu có ảnh thì xử lý tiếp
            if (images != null && !images.isEmpty()) {
                if (images.size() > ProductImage.MAXIMUM_IMAGES_PER_PRODUCT) {
                    return ResponseEntity.badRequest().body("Tối đa chỉ được upload 5 ảnh");
                }

                List<ProductImage> productImages = new ArrayList<>();
                for (MultipartFile file : images) {
                    if (file.isEmpty()) continue;

                    if (file.getSize() > 10 * 1024 * 1024) {
                        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("Ảnh vượt quá 10MB");
                    }

                    String contentType = file.getContentType();
                    if (contentType == null || !contentType.startsWith("image/")) {
                        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Chỉ chấp nhận ảnh");
                    }

                    // Lưu file
                    String fileName = storeFile(file);

                    // Lưu vào DB
                    ProductImageDTO dto = ProductImageDTO.builder().imageUrl(fileName).build();
                    ProductImage image = productService.createProductImage(updatedProduct.getId(), dto);
                    productImages.add(image);
                }
            }
            return ResponseEntity.ok("Cập nhật thành công");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private String storeFile(MultipartFile file) throws IOException {
        if(file.getOriginalFilename() == null){
            throw new IOException("Empty file name");
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        //Thêm UUID vào trước để đảm bảo fileName là duy nhất
        String uniqueFileName = UUID.randomUUID().toString() + "." + fileName;
        //Đường dẫn đến thư mục mà bạn muốn lưu file
        java.nio.file.Path uploadDir = Paths.get("uploads");
        //Kiểm tra và tạo nếu thư mục chưa tồn tại
        if(!Files.exists(uploadDir)){
            Files.createDirectories(uploadDir);
        }
        //Đường dẫn đầy đủ đến file
        java.nio.file.Path destination = Paths.get(uploadDir.toString(), uniqueFileName);
        //Sao chép file vào thư mục đích
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }


}
