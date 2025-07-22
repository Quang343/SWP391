package com.example.AgriculturalWarehouseManagement.Backend.mappers;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.CategoryWeightDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Category;
import com.example.AgriculturalWarehouseManagement.Backend.models.CategoryWeight;
import org.springframework.stereotype.Component;

@Component
public class CategoryWeightMapper {

    public CategoryWeightDTO toDTO(CategoryWeight entity) {
        if (entity == null) return null;

        return CategoryWeightDTO.builder()
                .categoryWeightId(entity.getCategoryWeightId())
                .categoryId(Math.toIntExact(entity.getCategory().getCategoryId()))
                .weight(entity.getWeight())
                .unit(entity.getUnit())
                .build();
    }

    public CategoryWeight toEntity(CategoryWeightDTO dto, Category category) {
        if (dto == null) return null;

        return CategoryWeight.builder()
                .categoryWeightId(dto.getCategoryWeightId())
                .category(category)
                .weight(dto.getWeight())
                .unit(dto.getUnit())
                .build();
    }
}
