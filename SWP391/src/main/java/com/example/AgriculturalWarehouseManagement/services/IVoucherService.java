package com.example.AgriculturalWarehouseManagement.services;

import com.example.AgriculturalWarehouseManagement.models.Voucher;
import com.example.AgriculturalWarehouseManagement.models.VoucherDTO;

import java.util.List;

public interface IVoucherService {
    Voucher createVoucher(VoucherDTO voucherDTO);

    Voucher updateVoucher(Long id, VoucherDTO voucherDTO);

    void deleteVoucher(Long id);

    Voucher findById(Long id);

    List<Voucher> findAll();

}
