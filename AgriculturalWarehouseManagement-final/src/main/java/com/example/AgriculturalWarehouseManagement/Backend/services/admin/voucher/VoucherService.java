package com.example.AgriculturalWarehouseManagement.Backend.services.admin.voucher;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.VoucherDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.DiscountType;
import com.example.AgriculturalWarehouseManagement.Backend.models.Voucher;
import com.example.AgriculturalWarehouseManagement.Backend.models.VoucherStatus;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.OrderRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoucherService implements IVoucherService {

    private final VoucherRepository voucherRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Voucher> findAll() {
        return voucherRepository.findAll();
    }

    @Override
    public List<Voucher> findByStatus(VoucherStatus status) {
        return voucherRepository.findByStatus(status);
    }

    @Override
    public List<Voucher> findAllSortedByDateOrStatus() {
        List<Voucher> vouchers = findAll();
        vouchers.sort(Comparator.comparing(Voucher::getEndDate).thenComparing(Voucher::getStatus));
        return vouchers;
    }

    @Override
    public boolean existsByVoucherCode(String voucherCode) {
        return voucherRepository.existsByVoucherCode(voucherCode);
    }

    @Override
    public Page<Voucher> findAll(Pageable pageable) {
        return voucherRepository.findAll(pageable);
    }

    @Override
    public List<Voucher> getApplicableVouchers(BigDecimal orderAmount) {
        return this.findByStatus(VoucherStatus.ACTIVE).stream()
                .filter(voucher -> voucher.getMinOrderAmount().compareTo(orderAmount) <= 0)
                .collect(Collectors.toList());
    }

    public void updateVoucherStatus(Voucher voucher){
        LocalDateTime now = LocalDateTime.now();
        VoucherStatus newStatus;
        if (now.isAfter(voucher.getEndDate())) newStatus = VoucherStatus.EXPIRED;
        else if (voucher.getUsedQuantity() != null && voucher.getQuantity() != null
                && voucher.getUsedQuantity() >= voucher.getQuantity()) {
            newStatus = VoucherStatus.INACTIVE;
        }else if (now.isBefore(voucher.getStartDate())) {
            newStatus = VoucherStatus.INACTIVE;
        } else {
            newStatus = VoucherStatus.ACTIVE;
        }

        voucher.setStatus(newStatus);
    }

    public void updateAllVoucherStatuses(){
        List<Voucher> vouchers = findAll();
        for(var voucher : vouchers) {
            updateVoucherStatus(voucher);
            voucherRepository.save(voucher);
        }
    }

    @Override
    public Voucher findById(Long id) {
        return voucherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voucher not found!"));
    }

    private void config(VoucherDTO voucherDTO){
        modelMapper.typeMap(VoucherDTO.class, Voucher.class)
                .addMappings(mapper -> mapper.skip(Voucher::setId))
                .addMappings(mapper -> mapper.skip(Voucher::setIsLocked));
    }

    @Override
    public Voucher createVoucher(VoucherDTO voucherDTO) throws Exception {
        if(voucherRepository.existsByVoucherCode(voucherDTO.getVoucherCode())){
            throw new Exception("Voucher code already exists");
        }

        if(voucherDTO.getEndDate().isBefore(voucherDTO.getStartDate())){
            throw new Exception("Invalid date");
        }

        config(voucherDTO);
        Voucher voucher = modelMapper.map(voucherDTO, Voucher.class);
        voucher.setUsedQuantity(0L);
        updateVoucherStatus(voucher);
        return voucherRepository.save(voucher);
    }

    @Override
    public Voucher updateVoucher(Long id, VoucherDTO voucherDTO) throws Exception {
        Voucher voucher = findById(id);
        if(!voucher.getVoucherCode().equals(voucherDTO.getVoucherCode())
                && voucherRepository.existsByVoucherCode(voucherDTO.getVoucherCode())){
            throw new Exception("Voucher code already exists");
        }

        if(voucherDTO.getEndDate().isBefore(voucherDTO.getStartDate())){
            throw new Exception("Invalid date");
        }

//        config(voucherDTO);
        voucher.setVoucherCode(voucherDTO.getVoucherCode());
        voucher.setDiscountType(DiscountType.valueOf(voucherDTO.getDiscountType()));
        voucher.setDiscountValue(voucherDTO.getDiscountValue());
        voucher.setQuantity(voucherDTO.getQuantity());
        voucher.setUsedQuantity(voucherDTO.getUsedQuantity());
        voucher.setMinOrderAmount(voucherDTO.getMinOrderAmount());
        voucher.setStartDate(voucherDTO.getStartDate());
        voucher.setEndDate(voucherDTO.getEndDate());
        updateVoucherStatus(voucher);
        return voucherRepository.save(voucher);
    }

    @Override
    public String deleteVoucher(Long id) {
        Voucher voucher = findById(id);
//        boolean hasBeenUsed = orderRepository.existsByVoucher(voucher);
        boolean hasBeenUsed = voucher.getUsedQuantity() > 0;
        if(hasBeenUsed){
//            voucher.setStatus(VoucherStatus.INACTIVE);
            voucher.setIsLocked(true);
            voucherRepository.save(voucher);
            return "Voucher đã từng được sử dụng và đã bị khoá.";
        }else{
            voucherRepository.deleteById(id);
            return "Voucher đã được xoá thành công.";
        }
    }

    public void unlockVoucher(Long id){
        Voucher voucher = findById(id);
        voucher.setIsLocked(false);
        voucherRepository.save(voucher);
    }

}
