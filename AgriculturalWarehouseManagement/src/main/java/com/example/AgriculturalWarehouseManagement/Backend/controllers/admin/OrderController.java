package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;


import com.example.AgriculturalWarehouseManagement.Backend.models.Order;
import com.example.AgriculturalWarehouseManagement.Backend.models.OrderStatus;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.order.OrderService;
import com.example.AgriculturalWarehouseManagement.Backend.utils.PaginationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/admin/orders")
    public String getAllOrders(Model model,
                               @RequestParam("page") Optional<String> page){
        int pageNumber = 1;
        try{
            if (page.isPresent()) {
                pageNumber = Integer.parseInt(page.get());
                if(pageNumber < 1){
                    pageNumber = 1;
                }
            }
            else{
                //pageNumber = 1
            }
        }catch (Exception e){
            //pageNumber = 1
            //Handle exception
        }
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        Page<Order> pageOrder = orderService.findAll(pageable);
        List<Order> orders = pageOrder.getContent();
        int totalPages = pageOrder.getTotalPages();
        model.addAttribute("orders", orders);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("pageNumbers", PaginationUtils.generatePageNumbers(totalPages, pageNumber));
        model.addAttribute("totalPages", totalPages);
//        List<Order> orders = orderService.findByStatusIsNot(OrderStatus.REMOVED.name());
        model.addAttribute("orders", orders);
        return "BackEnd/Admin/All_Orders";
    }



    @GetMapping("/warehouse/api/addstockout")
    public ResponseEntity<List<Order>> getPendingOrdersForStockOut() {
        List<Order> pendingOrders = orderService.findByStatus(OrderStatus.CONFIRMED.name());
        return ResponseEntity.ok(pendingOrders);
    }

    @GetMapping("/warehouse/api/returnstockout")
    public ResponseEntity<List<Order>> getReturnOrdersForStockOut() {
        List<Order> pendingOrders = orderService.findByStatus(OrderStatus.CANCELLED.name());
        return ResponseEntity.ok(pendingOrders);
    }

    @PostMapping("/admin/delete_order/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id){
        boolean isDeleted = orderService.deleteById(id);
        if(isDeleted){
            return ResponseEntity.ok().build();
        }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/admin/orders/{id}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String status = body.get("status");
        boolean isUpdated = orderService.updateOrderStatus(status, id);
        if(isUpdated) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/admin/orders/order_tracking")
    public String orderTracking() {
        return "BackEnd/Admin/FindOrderTracking";
    }

    @PostMapping("/admin/orders/order_tracking/{code}")
    public ResponseEntity<?> findOrderTrackingByIId(@PathVariable("code") String orderCode) {
        if(!orderService.existByOrderCode(orderCode)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Mã đơn hàng không tồn tại!"));
        }
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/admin/orders/order_tracking/{code}")
    public String getOrderTrackingPage(@PathVariable("code") String orderCode, Model model) {
        Order order = orderService.findByOrderCode(orderCode);
        model.addAttribute("order", order);
        return "/BackEnd/Admin/OrderTracking";
    }
}
