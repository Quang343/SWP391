package com.example.AgriculturalWarehouseManagement.services.order;

import com.example.AgriculturalWarehouseManagement.models.OrderDetail;

import java.util.List;

public interface IOrderDetailService{
    List<OrderDetail> findAll();
    List<OrderDetail> findByOrderId(Long orderId);
}
