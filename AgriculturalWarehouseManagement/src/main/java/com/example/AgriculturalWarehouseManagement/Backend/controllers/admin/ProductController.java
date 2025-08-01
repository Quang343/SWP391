package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.ProductDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.ProductImageDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.*;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.CategoryService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.NotificationService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.ProductImageService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.product.ProductService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.user.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Transactional
@Controller
//@RestController
@RequiredArgsConstructor
public class ProductController {

    @Value("${app.upload.product-dir}")
    private String uploadDir;

    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductImageService productImageService;
    private final ModelMapper modelMapper;
    private final NotificationService notificationService;

    @GetMapping("/admin/products")
    public String getAllProducts(Model model,
                                 @RequestParam("page") Optional<String> page,
                                 @RequestParam(required = false) Integer categoryId,
                                 @RequestParam(required = false) String status,
                                  HttpSession session
    ){
        int pageNumber = 1;
        try{
            if (page.isPresent()) {
                pageNumber = Integer.parseInt(page.get());
                if(pageNumber < 1){
                    pageNumber = 1;
                }
            }
            else{
                //pageNumber = 1
            }
        }catch (Exception e){
            //pageNumber = 1
            //Handle exception
        }
        Pageable pageable = PageRequest.of(pageNumber - 1, 3);
        Page<Product> productPage = productService.filterProducts(pageable, categoryId, status);
        List<Product> products = productPage.getContent();
        int totalPages = productPage.getTotalPages();
        model.addAttribute("products", products);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("status", status);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("pageNumbers", generatePageNumbers(totalPages, pageNumber));

        return "BackEnd/Admin/All_Products";
    }

    @GetMapping("/api/products/{productId}/name")
    public ResponseEntity<String> getProductName(@PathVariable Integer productId) {
        String productName = productService.getProductNameById(productId);
        if (productName == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productName);
    }

    private List<String> generatePageNumbers(int totalPages, int currentPage){
        List<String> pageNumbers = new ArrayList<>();
        if(totalPages <= 7){
            for(int i = 1; i <= totalPages; i++){
                pageNumbers.add(String.valueOf(i));
            }
        }else{
            if(currentPage <= 3){
                for(int i = 1; i <= currentPage + 1; i++){
                    pageNumbers.add(String.valueOf(i));
                }
                pageNumbers.add("...");
                pageNumbers.add(String.valueOf(totalPages));
            }else if(currentPage >= totalPages - 2){
                pageNumbers.addAll(List.of("1", "2", "..."));
                for(int i =  totalPages - 3; i <= totalPages; i++){
                    pageNumbers.add(String.valueOf(i));
                }
            }else{
                pageNumbers.addAll(List.of("1", "..."));
                for(int i = currentPage - 1; i <= currentPage + 1; i++){
                    pageNumbers.add(String.valueOf(i));
                }
                pageNumbers.add("...");
                pageNumbers.add(String.valueOf(totalPages));
            }
        }
        return pageNumbers;
    }

    @GetMapping("/admin/add_product")
    public String showCreateForm(Model model) {
        List<Category> categories = categoryService.findAll();
        List<String> statuses = getProductStatus();
        model.addAttribute("statuses", statuses);
        model.addAttribute("categories", categories);
        model.addAttribute("productDTO", new ProductDTO());
        return "BackEnd/Admin/Add_Product";
    }

    @PostMapping("/admin/saveProduct")
    public ResponseEntity<?> saveProduct(
            @ModelAttribute("productDTO") @Valid ProductDTO productDTO,
            BindingResult bindingResult,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach((error) -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        if (productService.existsByName(productDTO.getName())) {
            return ResponseEntity.badRequest().body(Map.of("name", "Product name already exists"));
        }
        try {
            Product product = productService.createProduct(productDTO);
            if (images != null && !images.isEmpty()) {
                ResponseEntity<?> imageUploadResult = handleImageUpload(product.getId(), images);
                if (!imageUploadResult.getStatusCode().is2xxSuccessful()) {
                    return imageUploadResult;
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body("Product created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private List<String> getProductStatus() {
        return Arrays.stream(ProductStatus.values())
                .map(Enum::name)
                .toList();
    }

    private void config() {
        modelMapper.typeMap(Product.class, ProductDTO.class);
    }

    @GetMapping("/admin/edit_product/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Product product = productService.findById(id);
        config();
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        List<String> statuses = getProductStatus();
        List<Gallery> images = productImageService.findAllByProduct(product);
        model.addAttribute("images", images);
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
    public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted)
            return ResponseEntity.ok().body(Map.of("message", "Xoá thành công"));
        else
            return ResponseEntity.badRequest().body(Map.of("message", "Xoá thất bại"));
    }


    @PutMapping(value = "/admin/update_product/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProductWithImages(
            @PathVariable int id,
            @Valid @ModelAttribute ProductDTO productDTO,
            BindingResult bindingResult,
            @RequestParam(value = "images", required = false) List<MultipartFile> images
    ) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = new HashMap<>();
                bindingResult.getFieldErrors().forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage()));
                return ResponseEntity.badRequest().body(errors);
            }

            Product product = productService.findById(id);
//            if (product == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
//            }

            if (!product.getName().equals(productDTO.getName().trim()) &&
                    productService.existsByName(productDTO.getName())) {
                return ResponseEntity.badRequest().body(
                        Map.of("name", "Product name already exists")
                );
            }

            productService.updateProduct(id, productDTO);

            if (images != null && !images.isEmpty()) {
                deleteOldFiles(product);
                productImageService.deleteAllByProduct(product);
                ResponseEntity<?> imageUploadResult = handleImageUpload(id, images);
                if (!imageUploadResult.getStatusCode().is2xxSuccessful()) {
                    return imageUploadResult;
                }
            }

            return ResponseEntity.ok("Cập nhật thành công");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private void deleteOldFiles(Product product){
        List<Gallery> images = productImageService.findAllByProduct(product);
        for(Gallery gallery : images){
            String oldFileName = gallery.getImageUrl();
            if(oldFileName != null && !oldFileName.isBlank()){
                deleteFile(oldFileName);
            }
        }
    }

    private ResponseEntity<?> handleImageUpload(int productId, List<MultipartFile> images) throws Exception {
        try {
//            if (images.size() > ProductImage.MAXIMUM_IMAGES_PER_PRODUCT) {
//                return ResponseEntity.badRequest().body("Tối đa chỉ được upload 5 ảnh");
//            }

            for (MultipartFile file : images) {
                if (file.isEmpty()) continue;

                if (file.getSize() > 10 * 1024 * 1024) {
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(Map.of("image", "Ảnh vượt quá 10MB"));
                }

                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(Map.of("imageType", "Chỉ chấp nhận ảnh"));
                }

                String fileName = storeFile(file);
                ProductImageDTO dto = new ProductImageDTO();
                dto.setImageUrl(fileName);
                productService.createProductImage(productId, dto);
            }
            return ResponseEntity.ok("Upload successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi khi lưu ảnh: " + e.getMessage());
        }
    }

    public void deleteFile(String fileName){
        try{
            Path filePath = Paths.get(uploadDir, "Seller").resolve(fileName);
            System.out.println("Deleting file: " + filePath.toAbsolutePath());
            Files.deleteIfExists(filePath);
        }catch (Exception e){
            System.err.println("Failed to delete old file: " + e.getMessage());
        }
    }

    private String storeFile(MultipartFile file) throws IOException {
        if (file.getOriginalFilename() == null) {
            throw new IOException("Empty file name");
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFileName = UUID.randomUUID().toString() + "." + fileName;
        Path uploadPath = Paths.get(uploadDir, "Seller");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        Path destination = uploadPath.resolve(uniqueFileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }
}
