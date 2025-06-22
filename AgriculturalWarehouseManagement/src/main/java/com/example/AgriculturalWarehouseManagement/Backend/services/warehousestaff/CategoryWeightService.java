package com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.CategoryWeightDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.CategoryWeightMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.Category;
import com.example.AgriculturalWarehouseManagement.Backend.models.CategoryWeight;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.CategoryRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.CategoryWeightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryWeightService {

    private final CategoryWeightRepository repository;
    private final CategoryWeightMapper mapper;
    private final CategoryRepository categoryRepository;

    public List<CategoryWeightDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoryWeightDTO findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("CategoryWeight not found with id: " + id));
    }

    public CategoryWeightDTO create(CategoryWeightDTO dto) {
        Category category = categoryRepository.findById(Long.valueOf(dto.getCategoryId()))
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + dto.getCategoryId()));

        CategoryWeight entity = mapper.toEntity(dto, category);
        return mapper.toDTO(repository.save(entity));
    }

    public CategoryWeightDTO update(Integer id, CategoryWeightDTO dto) {
        CategoryWeight existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("CategoryWeight not found with id: " + id));

        Category category = categoryRepository.findById(Long.valueOf(dto.getCategoryId()))
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + dto.getCategoryId()));

        existing.setWeight(dto.getWeight());
        existing.setUnit(dto.getUnit());

        return mapper.toDTO(repository.save(existing));
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("CategoryWeight not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
