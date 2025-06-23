package com.example.AgriculturalWarehouseManagement.Backend;

import com.example.AgriculturalWarehouseManagement.dtos.ProductImageDTO;
import com.example.AgriculturalWarehouseManagement.models.Product;
import com.example.AgriculturalWarehouseManagement.models.ProductImage;
import com.example.AgriculturalWarehouseManagement.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductService productService;

    @PostMapping(value = "/uploads/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImages(
            @Valid @PathVariable("id") Long id,
            @ModelAttribute("files") List<MultipartFile> files
    ){
        try {
            Product existingProduct = productService.findById(id);
            files = (files == null) ? new ArrayList<>() : files;
            if(files.size() > ProductImage.MAXIMUM_IMAGES_PER_PRODUCT){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Number of files not be exceeded 5");
            }
            List<ProductImage> productImages = new ArrayList<>();
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
                ProductImage productImage = productService.createProductImage
                        (existingProduct.getId(), productImageDTO);
                productImages.add(productImage);
            }
            return ResponseEntity.ok().body(productImages);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /*
    *
    {
    "name": "Ipad Pro",
    "price": 815.34,
    "url": "",
    "description": "This is a test product",
    "category_id": 1
    }
    * */

    //return name of file
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
