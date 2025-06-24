package com.example.AgriculturalWarehouseManagement.services.Seller;

import com.example.AgriculturalWarehouseManagement.dtos.Seller.ProductDetailResponseDTO;
import com.example.AgriculturalWarehouseManagement.dtos.Seller.ProductDetail_SellerDTO;
import com.example.AgriculturalWarehouseManagement.models.*;
import com.example.AgriculturalWarehouseManagement.repositories.Seller.CategoryWeight_SellerRepository;
import com.example.AgriculturalWarehouseManagement.repositories.Seller.ProductDetail_SellerRepository;
import com.example.AgriculturalWarehouseManagement.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductDetail_SellerService implements IProductDetail_SellerService {

    private final ProductDetail_SellerRepository productDetailRepository;
    private final ProductRepository productRepository;
    private final CategoryWeight_SellerRepository categoryWeightRepository;

    @Override
    public ProductDetail createProductDetail(ProductDetail_SellerDTO dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        Category category = product.getCategory();

        // Tìm hoặc tạo CategoryWeight
        CategoryWeight weight = categoryWeightRepository
                .findByCategoryAndWeightAndUnit(category, dto.getWeight(), dto.getUnit())
                .orElseGet(() -> {
                    CategoryWeight newWeight = new CategoryWeight();
                    newWeight.setCategory(category);
                    newWeight.setWeight(dto.getWeight());
                    newWeight.setUnit(dto.getUnit());
                    return categoryWeightRepository.save(newWeight);
                });

        // Kiểm tra nếu ProductDetail với Product + Weight này đã tồn tại
        boolean existsDetail = productDetailRepository.existsByProductIDAndCategoryWeightID(product, weight);
        if (existsDetail) {
            String formattedWeight = dto.getWeight() % 1 == 0 ? String.valueOf(dto.getWeight().intValue()) : dto.getWeight().toString();

            throw new RuntimeException("Đã tồn tại Product Detail với trọng lượng "
                    + formattedWeight + " " + dto.getUnit() + " cho sản phẩm này.");

        }

        // Tạo ProductDetail mới
        ProductDetail detail = ProductDetail.builder()
                .productID(product)
                .categoryWeightID(weight)
                .price(dto.getPrice())
                .expiry(dto.getExpiry())
                .detailName(dto.getWeight() + dto.getUnit())
                .status(ProductDetailStatus.valueOf(dto.getStatus()))
                .build();

        return productDetailRepository.save(detail);
    }


    @Override
    public ProductDetail updateProductDetail(Long id, ProductDetail_SellerDTO dto) {
        ProductDetail detail = findById(id);
        detail.setPrice(dto.getPrice());
        detail.setExpiry(dto.getExpiry());
        detail.setStatus(ProductDetailStatus.valueOf(dto.getStatus()));

        // update weight
        CategoryWeight weight = detail.getCategoryWeightID();
        weight.setWeight(dto.getWeight());
        weight.setUnit(dto.getUnit());
        categoryWeightRepository.save(weight);

        detail.setDetailName(dto.getWeight() + dto.getUnit());
        return productDetailRepository.save(detail);
    }

    @Override
    public boolean deleteProductDetail(Long id) {
        ProductDetail detail = findById(id);
        detail.setStatus(ProductDetailStatus.INACTIVE);
        productDetailRepository.save(detail);
        return true;
    }

    @Override
    public List<ProductDetail> getByProductId(Long productId) {
        return productDetailRepository.findByProductID_Id(productId);
    }

    @Override
    public ProductDetail findById(Long id) {
        return productDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy ProductDetail"));
    }

    @Override
    public List<ProductDetailResponseDTO> getProductDetailsByProductIdAsDTO(Long productId) {
        List<ProductDetail> details = productDetailRepository.findByProductID_Id(productId);
        return details.stream().map(detail -> ProductDetailResponseDTO.builder()
                .id(detail.getProductDetailId())
                .productName(detail.getProductID().getName())
                .detailName(detail.getDetailName())
                .price(detail.getPrice())
                .expiry(detail.getExpiry())
                .status(detail.getStatus().name())
                .build()
        ).collect(Collectors.toList());
    }

}
