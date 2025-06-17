package com.example.AgriculturalWarehouseManagement.repositories;

import com.example.AgriculturalWarehouseManagement.models.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    
}
