package com.example.AgriculturalWarehouseManagement.Backend.services.admin.product;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.BestSellerProductDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.ProductDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.ProductImageDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Category;
import com.example.AgriculturalWarehouseManagement.Backend.models.Gallery;
import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductStatus;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.CategoryRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductImageRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.WarehouseRepository;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.ProductStockProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final WarehouseRepository warehouseRepository;
    private final ProductImageRepository productImageRepository;

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = Product.builder()
                .name(productDTO.getName().trim())
                .description(productDTO.getDescription())
                .category(category)
                .status(ProductStatus.valueOf(productDTO.getStatus()))
                .build();
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(int id, ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = findById(id);
        product.setCategory(category);
        product.setName(productDTO.getName().trim());
        product.setDescription(productDTO.getDescription().trim());
        product.setStatus(ProductStatus.valueOf(productDTO.getStatus()));

        return productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setStatus(ProductStatus.INACTIVE);
            productRepository.save(product);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByNameIgnoreCase(name.trim());
    }

    @Override
    public Gallery createProductImage(int productId, ProductImageDTO productImageDTO) throws Exception {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new Exception
                        ("Product not found with id: " + productId));
        Gallery gallery = Gallery.builder()
                .product(product)
                .imageUrl(productImageDTO.getImageUrl())
                .build();
        //không cho insert quá 5 ảnh cho 1 sản phẩm
        int size = productImageRepository.findAllByProduct(product).size();
        if (size >= Gallery.MAXIMUM_IMAGES_PER_PRODUCT) {
            throw new Exception("Number of images must be <= " + Gallery.MAXIMUM_IMAGES_PER_PRODUCT);
        }
        return productImageRepository.save(gallery);
    }


    public String getProductNameById(Integer productId) {
        return productRepository.findById(productId)
                .map(product -> product.getName())
                .orElse(null);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    //    chiến_seller
    public void save(Product product) {
        productRepository.save(product);
    }

    public Page<Product> filterProducts(Pageable pageable, Integer categoryId, String status) {
        if (categoryId != null && status != null && !status.isEmpty()) {
            return productRepository.findByCategory_CategoryIdAndStatus(categoryId, ProductStatus.valueOf(status), pageable);
        } else if (categoryId != null) {
            return productRepository.findByCategory_CategoryId(categoryId, pageable);
        } else if (status != null && !status.isEmpty()) {
            return productRepository.findByStatus(ProductStatus.valueOf(status), pageable);
        } else {
            return productRepository.findAll(pageable);
        }
    }

    public int getProductQuantity(){
        return productRepository.getTotalRemainingAllProducts();
    }

    public List<ProductStockProjection> getAllProductStockProjections(){
        return productRepository.getRemainingStockByProduct();
    }

    public List<BestSellerProductDTO> getAllBestSellerProductDTO(){
        return productRepository.getTop5BestSellerProducts();
    }
}
