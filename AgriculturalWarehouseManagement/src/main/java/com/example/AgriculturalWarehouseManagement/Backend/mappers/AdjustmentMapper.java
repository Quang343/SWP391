package com.example.AgriculturalWarehouseManagement.Backend.mappers;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.AdjustmentDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Adjustment;
import com.example.AgriculturalWarehouseManagement.Backend.models.AdjustmentType;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductBatchRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class AdjustmentMapper {

    private final WarehouseRepository warehouseRepository;
    private final ProductBatchRepository productBatchRepository;

    public AdjustmentDTO toDTO(Adjustment adjustment) {
        return AdjustmentDTO.builder()
                .id(adjustment.getId())
                .warehouseId(adjustment.getWarehouse() != null ? adjustment.getWarehouse().getId() : null)
                .batchId(adjustment.getBatch() != null ? adjustment.getBatch().getBatchId() : null)
                .quantity(adjustment.getQuantity())
                .adjustDate(adjustment.getAdjustDate() != null ? adjustment.getAdjustDate().truncatedTo(ChronoUnit.SECONDS) : null)
                .reason(adjustment.getReason())
                .adjustmentType(adjustment.getAdjustmentType() != null ? adjustment.getAdjustmentType().name() : null)
                .build();
    }

    public Adjustment toEntity(AdjustmentDTO adjustmentDTO) {
        Adjustment adjustment = new Adjustment();
        adjustment.setId(adjustmentDTO.getId());
        adjustment.setQuantity(adjustmentDTO.getQuantity());
        adjustment.setAdjustDate(adjustmentDTO.getAdjustDate() != null
                ? adjustmentDTO.getAdjustDate().truncatedTo(ChronoUnit.SECONDS)
                : LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        adjustment.setReason(adjustmentDTO.getReason());
        adjustment.setAdjustmentType(adjustmentDTO.getAdjustmentType() != null
                ? AdjustmentType.valueOf(adjustmentDTO.getAdjustmentType())
                : null);

        if (adjustmentDTO.getWarehouseId() != null) {
            warehouseRepository.findById(adjustmentDTO.getWarehouseId())
                    .ifPresent(adjustment::setWarehouse);
        }

        if (adjustmentDTO.getBatchId() != null) {
            productBatchRepository.findById(adjustmentDTO.getBatchId())
                    .ifPresent(adjustment::setBatch);
        }

        return adjustment;
    }

    public void updateEntityFromDTO(AdjustmentDTO adjustmentDTO, Adjustment adjustment) {
        adjustment.setQuantity(adjustmentDTO.getQuantity());
        adjustment.setAdjustDate(adjustmentDTO.getAdjustDate() != null
                ? adjustmentDTO.getAdjustDate().truncatedTo(ChronoUnit.SECONDS)
                : LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        adjustment.setReason(adjustmentDTO.getReason());
        adjustment.setAdjustmentType(adjustmentDTO.getAdjustmentType() != null
                ? AdjustmentType.valueOf(adjustmentDTO.getAdjustmentType())
                : null);

        if (adjustmentDTO.getWarehouseId() != null) {
            warehouseRepository.findById(adjustmentDTO.getWarehouseId())
                    .ifPresent(adjustment::setWarehouse);
        }

        if (adjustmentDTO.getBatchId() != null) {
            productBatchRepository.findById(adjustmentDTO.getBatchId())
                    .ifPresent(adjustment::setBatch);
        }
    }
    }
