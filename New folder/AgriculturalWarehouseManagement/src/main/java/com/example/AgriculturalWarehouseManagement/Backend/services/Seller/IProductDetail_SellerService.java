package com.example.AgriculturalWarehouseManagement.Backend.services.Seller;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.Seller.ProductDetailResponseDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.Seller.ProductDetail_SellerDTO;
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
