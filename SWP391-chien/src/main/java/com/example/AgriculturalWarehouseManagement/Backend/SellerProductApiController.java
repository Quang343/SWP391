import com.example.AgriculturalWarehouseManagement.dtos.CategoryResponseDTO;
import com.example.AgriculturalWarehouseManagement.dtos.ProductDTO;
import com.example.AgriculturalWarehouseManagement.dtos.ProductResponseDTO;
import com.example.AgriculturalWarehouseManagement.models.Product;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@PostMapping
public ResponseEntity<?> createProduct(@RequestBody @Valid ProductDTO productDTO) {
    if (createProduct()) {
        return ResponseEntity.badRequest().body("Product name already exists");
    }

    try {
        Product product = createProduct();

        // Trả về DTO chứa đầy đủ thông tin product + category
        ProductResponseDTO response = new ProductResponseDTO();
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setStatus(product.getStatus());

        CategoryResponseDTO categoryDTO = new CategoryResponseDTO();
        categoryDTO.setId(product.getCategory().getId());
        categoryDTO.setName(product.getCategory().getName());
        categoryDTO.setDescription(product.getCategory().getDescription());
        categoryDTO.setStatus(product.getCategory().getStatus());

        response.setCategory(categoryDTO);

        return ResponseEntity.ok(response);
    } catch (Exception e) {
        return ResponseEntity.internalServerError().body("Error while creating product");
    }
}
