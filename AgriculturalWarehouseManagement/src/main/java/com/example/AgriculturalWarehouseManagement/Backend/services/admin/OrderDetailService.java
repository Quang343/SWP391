package com.example.AgriculturalWarehouseManagement.Backend.services.admin;


import com.example.AgriculturalWarehouseManagement.Backend.models.OrderDetail;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService implements IOrderDetailService{

    private final OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public List<OrderDetail> findByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

}
