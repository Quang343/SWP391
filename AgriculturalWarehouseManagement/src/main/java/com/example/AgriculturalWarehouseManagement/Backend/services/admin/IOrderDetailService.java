package com.example.AgriculturalWarehouseManagement.Backend.services.admin;



import com.example.AgriculturalWarehouseManagement.Backend.models.OrderDetail;

import java.util.List;

public interface IOrderDetailService{
    List<OrderDetail> findAll();
    List<OrderDetail> findOrderDetailsByOrderId(Long orderId);
}
