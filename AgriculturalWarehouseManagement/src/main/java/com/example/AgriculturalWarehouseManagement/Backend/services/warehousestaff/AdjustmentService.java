package com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.AdjustmentDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.AdjustmentMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.Adjustment;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.AdjustmentRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductBatchRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdjustmentService {
    private final AdjustmentRepository adjustmentRepository;
    private final WarehouseRepository warehouseRepository;
    private final ProductBatchRepository productBatchRepository;
    private final AdjustmentMapper adjustmentMapper;

    public Iterable<Adjustment> getAllAdjustments() {
        return adjustmentRepository.findAll();
    }

    public AdjustmentDTO getAdjustmentById(Integer id) {
        Adjustment adjustment = adjustmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adjustment not found with id: " + id));
        return adjustmentMapper.toDTO(adjustment);
    }



    public AdjustmentDTO createAdjustment(AdjustmentDTO adjustmentDTO) {
        Adjustment adjustment = adjustmentMapper.toEntity(adjustmentDTO);
        adjustment = adjustmentRepository.save(adjustment);
        return adjustmentMapper.toDTO(adjustment);
    }

    public AdjustmentDTO updateAdjustment(Integer id, AdjustmentDTO adjustmentDTO) {
        Adjustment existingAdjustment = adjustmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adjustment not found with id: " + id));

        adjustmentMapper.updateEntityFromDTO(adjustmentDTO, existingAdjustment);
        Adjustment updatedAdjustment = adjustmentRepository.save(existingAdjustment);
        return adjustmentMapper.toDTO(updatedAdjustment);
    }

    public void deleteAdjustment(Integer id) {
        if (!adjustmentRepository.existsById(id)) {
            throw new RuntimeException("Adjustment not found with id: " + id);
        }
        adjustmentRepository.deleteById(id);
    }
}
