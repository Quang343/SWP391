package com.example.AgriculturalWarehouseManagement.Backend.repositorys;


import com.example.AgriculturalWarehouseManagement.Backend.models.Voucher;
import com.example.AgriculturalWarehouseManagement.Backend.models.VoucherStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    List<Voucher> findByStatus(VoucherStatus status);
    boolean existsByVoucherCode(String voucherCode);
}
