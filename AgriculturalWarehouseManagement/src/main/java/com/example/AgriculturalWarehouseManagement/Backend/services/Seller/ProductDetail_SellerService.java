package com.example.AgriculturalWarehouseManagement.Backend.services.seller;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.ProductDetailResponseDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.ProductDetail_SellerDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.*;

import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.seller.CategoryWeight_SellerRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.seller.ProductDetail_SellerRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.seller.SoldBySellerRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.seller.User_SellerRepository;
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
    private final SoldBySellerRepository soldBySellerRepository;
    private final User_SellerRepository userRepository;

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

        // Kiểm tra trùng
        String formattedWeight = dto.getWeight() % 1 == 0
                ? String.valueOf(dto.getWeight().intValue())
                : dto.getWeight().toString();

        boolean existsDetail = productDetailRepository.existsByProductIDAndCategoryWeightID(product, weight);
        if (existsDetail) {
            throw new RuntimeException("Đã tồn tại chi tiết sản phẩm với trọng lượng "
                    + formattedWeight + " " + dto.getUnit() + " cho sản phẩm này.");
        }

        // ✅ Tạo ProductDetail
        ProductDetail detail = ProductDetail.builder()
                .productID(product)
                .categoryWeightID(weight)
                .price(dto.getPrice())
                .expiry(dto.getExpiry())
                .detailName(formattedWeight + " " + dto.getUnit())
                .status(ProductDetailStatus.valueOf(dto.getStatus()))
                .build();

        detail = productDetailRepository.save(detail);

        // ✅ Gán user (seller) → Bảng SoldBySeller
        User seller = userRepository.findById(dto.getUserId().intValue())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        SoldBySeller link = new SoldBySeller();
        link.setProductDetail(detail);
        link.setUser(seller);
        soldBySellerRepository.save(link);

        return detail;
    }

    @Override
    public ProductDetail updateProductDetail(Long id, ProductDetail_SellerDTO dto) {
        ProductDetail detail = findById(id);
        Product product = detail.getProductID();
        Category category = product.getCategory();

        // Tìm hoặc tạo CategoryWeight
        CategoryWeight newWeight = categoryWeightRepository
                .findByCategoryAndWeightAndUnit(category, dto.getWeight(), dto.getUnit())
                .orElseGet(() -> {
                    CategoryWeight created = new CategoryWeight();
                    created.setCategory(category);
                    created.setWeight(dto.getWeight());
                    created.setUnit(dto.getUnit());
                    return categoryWeightRepository.save(created);
                });

        // Kiểm tra nếu thay đổi weight + unit → thì check trùng
        if (!detail.getCategoryWeightID().getCategoryWeightId().equals(newWeight.getCategoryWeightId())) {
            boolean exists = productDetailRepository.existsByProductIDAndCategoryWeightIDAndProductDetailIdNot(
                    product, newWeight, id
            );
            if (exists) {
                String formattedWeight = dto.getWeight() % 1 == 0
                        ? String.valueOf(dto.getWeight().intValue())
                        : dto.getWeight().toString();

                throw new RuntimeException("Đã tồn tại chi tiết sản phẩm khác với trọng lượng "
                        + formattedWeight + " " + dto.getUnit() + " cho sản phẩm này.");
            }
        }

        // Update các trường còn lại
        detail.setCategoryWeightID(newWeight); // gán lại nếu weight đổi
        detail.setPrice(dto.getPrice());
        detail.setExpiry(dto.getExpiry());
        detail.setStatus(ProductDetailStatus.valueOf(dto.getStatus()));

        // Format lại tên
        String formattedWeight = dto.getWeight() % 1 == 0
                ? String.valueOf(dto.getWeight().intValue())
                : dto.getWeight().toString();
        detail.setDetailName(formattedWeight + " " + dto.getUnit());

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
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chi tiết sản phẩm"));
    }

    @Override
    public List<ProductDetailResponseDTO> getProductDetailsByProductIdAsDTO(Long productId) {
        List<ProductDetail> details = productDetailRepository.findByProductID_Id(productId);
        return details.stream().map(detail -> {
            List<SoldBySeller> sellers = soldBySellerRepository.findByProductDetail(detail);
            String email = sellers.isEmpty() ? "N/A" : sellers.get(0).getUser().getEmail(); // giả sử lấy 1 seller đầu tiên

            return ProductDetailResponseDTO.builder()
                    .id(detail.getProductDetailId())
                    .productName(detail.getProductID().getName())
                    .detailName(detail.getDetailName())
                    .price(detail.getPrice())
                    .expiry(detail.getExpiry())
                    .status(detail.getStatus().name())
                    .sellerEmail(email) // ✅ gán email
                    .build();
        }).collect(Collectors.toList());
    }


}
