package com.example.AgriculturalWarehouseManagement.Backend.services.admin.voucher;



import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.VoucherDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Voucher;
import com.example.AgriculturalWarehouseManagement.Backend.models.VoucherStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface IVoucherService {
    Voucher createVoucher(VoucherDTO voucherDTO) throws Exception;

    Voucher updateVoucher(Long id, VoucherDTO voucherDTO) throws Exception;

    String deleteVoucher(Long id);

    Voucher findById(Long id);

    List<Voucher> findAll();

    List<Voucher> findByStatus(VoucherStatus status);

    List<Voucher> findAllSortedByDateOrStatus();

    boolean existsByVoucherCode(String voucherCode);

    Page<Voucher> findAll(Pageable pageable);

    List<Voucher> getApplicableVouchers(BigDecimal orderAmount);
}
