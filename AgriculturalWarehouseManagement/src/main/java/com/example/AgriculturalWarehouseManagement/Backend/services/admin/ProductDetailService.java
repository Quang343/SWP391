package com.example.AgriculturalWarehouseManagement.Backend.services.admin;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.ProductDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.ProductDetailMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductDetail;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductDetailService {
    @Autowired
    private ProductDetailRepository repository;

    @Autowired
    private ProductDetailMapper mapper;
    @Autowired
    private ProductDetailRepository productDetailRepository;

    public ProductDetailDTO create(ProductDetailDTO dto) {
        ProductDetail entity = mapper.productDetailDTOToProductDetail(dto);
        ProductDetail savedEntity = repository.save(entity);
        return mapper.productDetailToProductDetailDTO(savedEntity);
    }

    public ProductDetailDTO update(Integer id, ProductDetailDTO dto) {
        ProductDetail entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductDetail not found"));
        mapper.updateProductDetailFromDTO(dto, entity); // Update entity with DTO values
        ProductDetail updatedEntity = repository.save(entity);
        return mapper.productDetailToProductDetailDTO(updatedEntity);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("ProductDetail not found");
        }
        repository.deleteById(id);
    }

    public ProductDetailDTO findById(Integer id) {
        return repository.findById(id)
                .map(mapper::productDetailToProductDetailDTO)
                .orElseThrow(() -> new RuntimeException("ProductDetail not found"));
    }

    public List<ProductDetailDTO> findAll() {
        List<ProductDetailDTO> result = new ArrayList<>();
        for (ProductDetail detail : repository.findAll()) {
            result.add(mapper.productDetailToProductDetailDTO(detail));
        }
        return result;
    }

    public List<ProductDetail> getAllByProduct(Product product) {
        return productDetailRepository.findProductDetailsByProductID(product);
    }

}
