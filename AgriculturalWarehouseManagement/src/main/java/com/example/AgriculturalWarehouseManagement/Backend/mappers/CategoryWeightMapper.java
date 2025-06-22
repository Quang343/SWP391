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
                .categoryWeightId(entity.getId())
                .categoryId(Math.toIntExact(entity.getCategoryID().getId()))
                .weight(entity.getWeight())
                .unit(entity.getUnit())
                .build();
    }

    public CategoryWeight toEntity(CategoryWeightDTO dto, Category category) {
        if (dto == null) return null;

        return CategoryWeight.builder()
                .id(dto.getCategoryWeightId())
                .categoryID(category)
                .weight(dto.getWeight())
                .unit(dto.getUnit())
                .build();
    }
}
