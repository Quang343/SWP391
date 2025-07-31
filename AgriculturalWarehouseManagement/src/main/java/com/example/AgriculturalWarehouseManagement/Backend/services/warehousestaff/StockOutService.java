package com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.OrderDTO_WareHouse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockOutDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockOutDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.StockOutDetailMapper;
import com.example.AgriculturalWarehouseManagement.Backend.mappers.StockOutMapper;
import com.example.AgriculturalWarehouseManagement.Backend.models.*;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.*;

import com.example.AgriculturalWarehouseManagement.Backend.services.admin.order.OrderDetailService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.order.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StockOutService {

    @Autowired
    private StockOutDetailRepository stockOutDetailRepository;

    @Autowired
    private StockOutRepository stockOutRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockOutMapper stockOutMapper;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductBatchRepository productBatchRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private StockOutDetailService stockOutDetailService;

    @Autowired
    private StockOutDetailMapper stockOutDetailMapper;

    public List<StockOutDTO> getAllStockOuts() {
        return stockOutRepository.findAll().stream()
                .map(stockOutMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Page<StockOut> getPaginatedStockOut(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StockOut> stockOutPage = stockOutRepository.findPaginatedStockOut(pageable);
        // Debug output
        System.out.println("Debug - Page: " + page + ", Size: " + size +
                ", Total Pages: " + stockOutPage.getTotalPages() +
                ", Total Elements: " + stockOutPage.getTotalElements());
        return stockOutPage;
    }

    public StockOutDTO getStockOutById(Integer id) {
        StockOut stockOut = stockOutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StockOut not found"));
        return stockOutMapper.toDTO(stockOut);
    }

    @Transactional
    public StockOutDTO createStockOut(StockOutDTO stockOutDTO) {
        System.out.println("Debug - Creating StockOut with DTO: " + stockOutDTO);
        if (stockOutDTO.getWarehouseID() == null) {
            throw new RuntimeException("WarehouseID cannot be null");
        }
        if (stockOutDTO.getOrderID() == null) {
            throw new RuntimeException("OrderID cannot be null");
        }
        StockOut stockOut = stockOutMapper.toEntity(stockOutDTO);
        Warehouse warehouse = warehouseRepository.findById(stockOutDTO.getWarehouseID())
                .orElseThrow(() -> new RuntimeException("Warehouse not found for ID: " + stockOutDTO.getWarehouseID()));
        Order order = orderRepository.findById(Long.valueOf(stockOutDTO.getOrderID()))
                .orElseThrow(() -> new RuntimeException("Order not found for ID: " + stockOutDTO.getOrderID()));
        stockOut.setWarehouseID(warehouse);
        stockOut.setOrderID(order);
        // Use provided stockOutDate if present, otherwise use current time
        stockOut.setStockOutDate(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));
        stockOut.setStatus(StockOutStatus.EXPORTED);

        System.out.println("Debug - Saving StockOut: " + stockOut);
        StockOut savedStockOut = stockOutRepository.save(stockOut);

        // Create and save StockOutDetails
        List<OrderDetail> orderDetails = orderDetailService.findOrderDetailsByOrderId(Long.valueOf(stockOutDTO.getOrderID()));
        if (orderDetails.isEmpty()) {
            throw new RuntimeException("No OrderDetails found for orderID: " + stockOutDTO.getOrderID());
        }
        System.out.println("Debug - Found OrderDetails: " + orderDetails.size());
        List<StockOutDetailDTO> stockOutDetailDTOs = stockOutDetailService.createStockOutDetailsFromOrderDetails(savedStockOut.getId(), orderDetails);
        System.out.println("Debug - Created StockOutDetailDTOs: " + stockOutDetailDTOs.size());
        stockOutDetailService.saveStockOutDetails(stockOutDetailDTOs);

        // Update Order status to STOCKOUT
        System.out.println("Debug - Updating Order ID: " + order.getId() + " status to STOCKOUT");
        order.setStatus("STOCKOUT");
        orderRepository.save(order);
        System.out.println("Debug - Order ID: " + order.getId() + " status updated to: " + order.getStatus());

        return stockOutMapper.toDTO(savedStockOut);
    }

    @Transactional
    public List<StockOut> getStockOutsByOrderId(Long orderId) {
        return stockOutRepository.findByOrderID_Id(orderId);
    }

    @Transactional
    public List<StockOutDetailDTO> processCanceledOrderReturns() {
        List<StockOutDetailDTO> returnedDetailsToDisplay = new ArrayList<>();

        // 1. Tìm các Order bị hủy
        List<OrderDTO_WareHouse> canceledOrders = orderService.findByStatus("CANCELLED");
        if (canceledOrders.isEmpty()) return returnedDetailsToDisplay;

        // Lấy danh sách orderId từ các Order bị hủy
        List<Long> canceledOrderIds = canceledOrders.stream()
                .map(OrderDTO_WareHouse::getOrderId) // ✅ Sửa lại
                .collect(Collectors.toList());

        // 2. Tìm các StockOut có status = RETURNED và thuộc các đơn bị hủy
        List<StockOut> returnedStockOutsForDisplay = stockOutRepository
                .findByOrderID_IdInAndStatus(canceledOrderIds, StockOutStatus.RETURNED);

        List<Integer> returnedStockOutIdsForDisplay = returnedStockOutsForDisplay.stream()
                .map(StockOut::getId)
                .collect(Collectors.toList());

        List<StockOutDetail> returnedDetailsForDisplay = stockOutDetailRepository
                .findByStockOutID_IdIn(returnedStockOutIdsForDisplay);

        // 3. RESET soldQuantity về 0 cho tất cả ProductBatch
        List<ProductBatch> allBatches = (List<ProductBatch>) productBatchRepository.findAll();
        for (ProductBatch batch : allBatches) {
            batch.setSoldQuantity(0);
        }

        // 4. Cộng lại soldQuantity từ các StockOut có status = EXPORTED
        List<StockOut> exportedStockOuts = stockOutRepository.findByStatus(StockOutStatus.EXPORTED);
        List<Integer> exportedStockOutIds = exportedStockOuts.stream()
                .map(StockOut::getId)
                .collect(Collectors.toList());

        List<StockOutDetail> allExportedDetails = stockOutDetailRepository
                .findByStockOutID_IdIn(exportedStockOutIds);

        for (StockOutDetail detail : allExportedDetails) {
            ProductBatch batch = detail.getBatchID();
            batch.setSoldQuantity(batch.getSoldQuantity() + detail.getQuantity());
        }

        productBatchRepository.saveAll(allBatches);

        // 5. Chuẩn bị danh sách hiển thị (dòng RETURNED)
        for (StockOutDetail detail : returnedDetailsForDisplay) {
            StockOutDetailDTO dto = stockOutDetailMapper.toDTO(detail);
            dto.setQuantity(-detail.getQuantity()); // Hiển thị âm để biểu thị là trả hàng
            returnedDetailsToDisplay.add(dto);
        }

        return returnedDetailsToDisplay;
    }


    public List<StockOutDTO> getCanceledOrdersWithExportedStockOuts() {
        // Lấy danh sách Order có status = CANCELLED
        List<OrderDTO_WareHouse> canceledOrders = orderService.findByStatus("CANCELLED");
        if (canceledOrders.isEmpty()) {
            return List.of(); // Trả về danh sách rỗng nếu không có Order bị hủy
        }


        // Lấy danh sách orderId từ các Order bị hủy
        List<Long> canceledOrderIds = canceledOrders.stream()
                .map(OrderDTO_WareHouse::getOrderId) // ✅ Sửa lại
                .collect(Collectors.toList());

        // Lấy tất cả StockOut liên quan có status = EXPORTED
        List<StockOut> exportedStockOuts = stockOutRepository.findByOrderID_IdInAndStatus(canceledOrderIds, StockOutStatus.EXPORTED);
        return exportedStockOuts.stream()
                .map(stockOutMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public StockOutDTO updateStockOutStatusToReturned(Integer stockOutId) {
        StockOut stockOut = stockOutRepository.findById(stockOutId)
                .orElseThrow(() -> new RuntimeException("StockOut not found for ID: " + stockOutId));
        stockOut.setStatus(StockOutStatus.RETURNED);
        StockOut updatedStockOut = stockOutRepository.save(stockOut);
        return stockOutMapper.toDTO(updatedStockOut);
    }

    public List<Map<String, Object>> getTotalQuantityByMonth() {
        List<Object[]> results = stockOutRepository.findTotalQuantityMonth();
        return results.stream()
                .map(result -> {
                    Integer year = (Integer) result[0];
                    Integer month = (Integer) result[1];
                    Long totalQuantity = (Long) result[2];
                    String formattedMonth = String.format("%d-%02d", year, month);
                    Map<String, Object> map = new HashMap<>();
                    map.put("month", formattedMonth);
                    map.put("totalQuantity", totalQuantity);
                    return map;
                })
                .collect(Collectors.toList());
    }

}
