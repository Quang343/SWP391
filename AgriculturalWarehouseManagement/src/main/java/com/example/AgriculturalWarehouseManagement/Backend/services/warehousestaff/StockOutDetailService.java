package com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.OrderDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.ProductBatchDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockOutDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.StockOutDetailMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.OrderDetail;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductBatch;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockOut;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockOutDetail;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.OrderDetailRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductBatchRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.StockOutDetailRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.StockOutRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockOutDetailService {
    @Autowired
    private StockOutDetailRepository stockOutDetailRepository;

    @Autowired
    private StockOutRepository stockOutRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductBatchRepository productBatchRepository;

    @Autowired
    private StockOutDetailMapper stockOutDetailMapper;

    @Autowired
    private ProductBatchService productBatchService;

    public List<StockOutDetailDTO> getAllStockOutDetails() {
        return stockOutDetailRepository.findAll().stream()
                .map(stockOutDetailMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StockOutDetailDTO getStockOutDetailById(Integer id) {
        StockOutDetail stockOutDetail = stockOutDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StockOutDetail not found"));
        return stockOutDetailMapper.toDTO(stockOutDetail);
    }

    public List<StockOutDetailDTO> getStockOutDetailsByStockOutId(Integer stockOutID) {
        return stockOutDetailRepository.findByStockOutID_Id(stockOutID).stream()
                .map(stockOutDetailMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StockOutDetailDTO createStockOutDetail(StockOutDetailDTO stockOutDetailDTO) {
        if (stockOutDetailDTO.getStockOutID() == null) {
            throw new RuntimeException("StockOutID cannot be null");
        }
        if (stockOutDetailDTO.getOrderDetailID() == null) {
            throw new RuntimeException("OrderDetailID cannot be null");
        }
        if (stockOutDetailDTO.getBatchID() == null) {
            throw new RuntimeException("BatchID cannot be null");
        }
        stockOutRepository.findById(stockOutDetailDTO.getStockOutID())
                .orElseThrow(() -> new RuntimeException("StockOut not found for ID: " + stockOutDetailDTO.getStockOutID()));
        StockOutDetail stockOutDetail = stockOutDetailMapper.toEntity(stockOutDetailDTO);
        StockOutDetail savedStockOutDetail = stockOutDetailRepository.save(stockOutDetail);
        // Update ProductBatch soldQuantity
        ProductBatch productBatch = productBatchRepository.findById(stockOutDetailDTO.getBatchID())
                .orElseThrow(() -> new RuntimeException("ProductBatch not found for ID: " + stockOutDetailDTO.getBatchID()));
        productBatch.setSoldQuantity(productBatch.getSoldQuantity() + stockOutDetailDTO.getQuantity());
        productBatchRepository.save(productBatch);
        return stockOutDetailMapper.toDTO(savedStockOutDetail);
    }

    public StockOutDetailDTO updateStockOutDetail(Integer id, StockOutDetailDTO stockOutDetailDTO) {
        StockOutDetail stockOutDetail = stockOutDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StockOutDetail not found"));
        stockOutDetail.setQuantity(stockOutDetailDTO.getQuantity());
        if (stockOutDetailDTO.getStockOutID() != null) {
            StockOut stockOut = stockOutRepository.findById(stockOutDetailDTO.getStockOutID())
                    .orElseThrow(() -> new RuntimeException("StockOut not found"));
            stockOutDetail.setStockOutID(stockOut);
        }
        if (stockOutDetailDTO.getOrderDetailID() != null) {
            OrderDetail orderDetail = orderDetailRepository.findById(Long.valueOf(stockOutDetailDTO.getOrderDetailID()))
                    .orElseThrow(() -> new RuntimeException("OrderDetail not found"));
            stockOutDetail.setOrderDetailID(orderDetail);
        }
        if (stockOutDetailDTO.getBatchID() != null) {
            ProductBatch productBatch = productBatchRepository.findById(stockOutDetailDTO.getBatchID())
                    .orElseThrow(() -> new RuntimeException("ProductBatch not found"));
            stockOutDetail.setBatchID(productBatch);
        }
        StockOutDetail updatedStockOutDetail = stockOutDetailRepository.save(stockOutDetail);
        return stockOutDetailMapper.toDTO(updatedStockOutDetail);
    }

    public void deleteStockOutDetail(Integer id) {
        stockOutDetailRepository.deleteById(id);
    }

    public List<StockOutDetailDTO> createStockOutDetailsFromOrderDetails(Integer stockOutId, List<OrderDetail> orderDetails) {
        if (stockOutId == null) {
            throw new RuntimeException("StockOutID cannot be null");
        }
        if (orderDetails == null || orderDetails.isEmpty()) {
            throw new RuntimeException("OrderDetails list cannot be null or empty");
        }
        stockOutRepository.findById(stockOutId)
                .orElseThrow(() -> new RuntimeException("StockOut not found for ID: " + stockOutId));

        List<StockOutDetailDTO> stockOutDetails = new ArrayList<>();
        for (int i = 0; i < orderDetails.size(); i++) {
            OrderDetail orderDetail = orderDetails.get(i);

            if (orderDetail == null) {
                throw new RuntimeException("OrderDetail at index " + i + " cannot be null");
            }
            if (orderDetail.getId() == null) {
                throw new RuntimeException("OrderDetailId cannot be null at index " + i);
            }
            if (orderDetail.getProductDetailId() == null) {
                throw new RuntimeException("ProductDetailId cannot be null for OrderDetail at index " + i);
            }

            // Fetch all batches for the productDetailId, ordered by manufactureDate
            List<ProductBatchDTO> batches = productBatchService.getProductBatchesByProductDetailId(orderDetail.getProductDetailId());
            if (batches.isEmpty()) {
                throw new RuntimeException("No batch found for productDetailId: " + orderDetail.getProductDetailId());
            }

            int remainingOrderQuantity = orderDetail.getQuantity();
            for (ProductBatchDTO batch : batches) {
                if (remainingOrderQuantity <= 0) {
                    break; // All required quantity fulfilled
                }
                if (batch.getBatchID() == null) {
                    throw new RuntimeException("BatchID cannot be null for productDetailId: " + orderDetail.getProductDetailId());
                }

                // Calculate remaining quantity for the batch
                int remainingBatchQuantity = (batch.getImportedQuantity() != null ? batch.getImportedQuantity() : 0) -
                        (batch.getSoldQuantity() != null ? batch.getSoldQuantity() : 0) +
                        (batch.getTotalAdjustment() != null ? batch.getTotalAdjustment() : 0);

                if (remainingBatchQuantity <= 0) {
                    continue; // Skip batches with no remaining quantity
                }

                // Determine quantity to use from this batch
                int quantityToUse = Math.min(remainingOrderQuantity, remainingBatchQuantity);
                StockOutDetailDTO stockOutDetail = new StockOutDetailDTO();
                stockOutDetail.setStockOutID(stockOutId);
                stockOutDetail.setOrderDetailID(Math.toIntExact(orderDetail.getId()));
                stockOutDetail.setBatchID(batch.getBatchID());
                stockOutDetail.setQuantity(quantityToUse);
                stockOutDetails.add(stockOutDetail);

                // Update remaining quantity
                remainingOrderQuantity -= quantityToUse;
            }

            if (remainingOrderQuantity > 0) {
                throw new RuntimeException("Insufficient stock for productDetailId: " + orderDetail.getProductDetailId() +
                        ", required: " + orderDetail.getQuantity() + ", remaining: " + (orderDetail.getQuantity() - remainingOrderQuantity));
            }
        }

        return stockOutDetails;
    }

    @Transactional
    public void saveStockOutDetails(List<StockOutDetailDTO> stockOutDetailDTOs) {
        if (stockOutDetailDTOs == null || stockOutDetailDTOs.isEmpty()) {
            throw new RuntimeException("StockOutDetailDTOs list cannot be null or empty");
        }
        List<StockOutDetail> stockOutDetails = stockOutDetailDTOs.stream()
                .map(dto -> {
                    if (dto.getStockOutID() == null || dto.getOrderDetailID() == null || dto.getBatchID() == null) {
                        throw new RuntimeException("StockOutDetailDTO has null fields: stockOutID=" + dto.getStockOutID() +
                                ", orderDetailID=" + dto.getOrderDetailID() + ", batchID=" + dto.getBatchID());
                    }
                    stockOutRepository.findById(dto.getStockOutID())
                            .orElseThrow(() -> new RuntimeException("StockOut not found for ID: " + dto.getStockOutID()));
                    StockOutDetail stockOutDetail = stockOutDetailMapper.toEntity(dto);
                    if (stockOutDetail.getStockOutID() == null || stockOutDetail.getOrderDetailID() == null || stockOutDetail.getBatchID() == null) {
                        throw new RuntimeException("Mapped StockOutDetail has null fields: stockOutID=" + stockOutDetail.getStockOutID() +
                                ", orderDetailID=" + stockOutDetail.getOrderDetailID() + ", batchID=" + stockOutDetail.getBatchID());
                    }
                    return stockOutDetail;
                })
                .collect(Collectors.toList());
        // Save StockOutDetails
        stockOutDetailRepository.saveAll(stockOutDetails);
        // Update ProductBatch soldQuantity
        for (StockOutDetailDTO dto : stockOutDetailDTOs) {
            ProductBatch productBatch = productBatchRepository.findById(dto.getBatchID())
                    .orElseThrow(() -> new RuntimeException("ProductBatch not found for ID: " + dto.getBatchID()));
            productBatch.setSoldQuantity(productBatch.getSoldQuantity() + dto.getQuantity());
            productBatchRepository.save(productBatch);
        }
    }
}