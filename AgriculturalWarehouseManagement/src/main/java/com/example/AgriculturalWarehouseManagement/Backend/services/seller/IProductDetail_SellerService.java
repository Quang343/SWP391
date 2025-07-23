package com.example.AgriculturalWarehouseManagement.Backend.services.seller;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.ProductDetailResponseDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.ProductDetail_SellerDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductDetail;

import java.util.List;

public interface IProductDetail_SellerService {

    ProductDetail createProductDetail(ProductDetail_SellerDTO dto);

    ProductDetail updateProductDetail(Long id, ProductDetail_SellerDTO dto);

    boolean deleteProductDetail(Long id);

    ProductDetail findById(Long id);

    List<ProductDetail> getByProductId(Long productId);

    List<ProductDetailResponseDTO> getProductDetailsByProductIdAsDTO(Long productId);

}
