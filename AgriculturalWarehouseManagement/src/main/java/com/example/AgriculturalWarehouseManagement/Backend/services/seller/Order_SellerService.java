package com.example.AgriculturalWarehouseManagement.Backend.services.seller;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.Order_SellerDTO;

import java.util.List;

public interface Order_SellerService {
    List<Order_SellerDTO> getAllOrders_Seller();
    void confirmOrder(Long orderId);
    Order_SellerDTO getOrderById(Long orderId);

}
