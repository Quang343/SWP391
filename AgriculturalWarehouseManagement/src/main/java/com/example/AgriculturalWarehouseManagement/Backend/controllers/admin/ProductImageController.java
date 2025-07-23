package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.ProductImageDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Gallery;
import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.IProductImageService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductService productService;

    @Autowired
    private IProductImageService productImageService;

    @Value("${app.upload.product-dir}")
    private String uploadDir;

    @PostMapping(value = "/uploads/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImages(
            @Valid @PathVariable("id") int id,
            @ModelAttribute("files") List<MultipartFile> files
    ){
        try {
            Product existingProduct = productService.findById(id);
            files = (files == null) ? new ArrayList<>() : files;
            if(files.size() > Gallery.MAXIMUM_IMAGES_PER_PRODUCT){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Number of files not be exceeded 5");
            }
            List<Gallery> galleries = new ArrayList<>();
            for(MultipartFile file : files) {
                //kiểm tra kích thước file và định dạng
                if (file.getSize() == 0) continue;
                if (file.getSize() > 10 * 1024 * 1024) { // > 10 MB
                    //throw new ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE, "File is too large");
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File is too large");
                }
                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("File must be an image");
                }
                //Lưu file và cập nhật url
                String fileName = storeFile(file);
                //luu vao doi tuong product trong db
                ProductImageDTO productImageDTO = new ProductImageDTO();
                productImageDTO.setImageUrl(fileName);
                Gallery gallery = productService.createProductImage
                        (existingProduct.getId(), productImageDTO);
                galleries.add(gallery);
            }
            return ResponseEntity.ok().body(galleries);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //return name of file
    private String storeFile(MultipartFile file) throws IOException {
        if(file.getOriginalFilename() == null){
            throw new IOException("Empty file name");
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        //Thêm UUID vào trước để đảm bảo fileName là duy nhất
        String uniqueFileName = UUID.randomUUID().toString() + "." + fileName;
        //Đường dẫn đến thư mục mà bạn muốn lưu file
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

    @GetMapping("/api/products/first/{productId}")
    public ResponseEntity<String> getFirstImageUrlByProductId(@PathVariable int productId) {
        Product product = productService.findById(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        Gallery firstImage = productImageService.findFirstByProduct(product);
        if (firstImage == null || firstImage.getImageUrl() == null) {
            return ResponseEntity.ok("assets/images/default.jpg"); // Ảnh mặc định nếu không có
        }
        return ResponseEntity.ok(firstImage.getImageUrl());
    }
}
