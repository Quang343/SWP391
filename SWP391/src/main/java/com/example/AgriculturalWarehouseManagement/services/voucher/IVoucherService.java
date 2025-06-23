package com.example.AgriculturalWarehouseManagement.services.voucher;

import com.example.AgriculturalWarehouseManagement.models.Voucher;
import com.example.AgriculturalWarehouseManagement.dtos.VoucherDTO;
import com.example.AgriculturalWarehouseManagement.models.VoucherStatus;

import java.util.List;
import java.util.Optional;

public interface IVoucherService {
    Voucher createVoucher(VoucherDTO voucherDTO) throws Exception;

    Voucher updateVoucher(Long id, VoucherDTO voucherDTO) throws Exception;

    String deleteVoucher(Long id);

    Voucher findById(Long id);

    List<Voucher> findAll();

    List<Voucher> findByStatus(VoucherStatus status);

    List<Voucher> findAllSortedByDateOrStatus();

    boolean existsByVoucherCode(String voucherCode);

}
