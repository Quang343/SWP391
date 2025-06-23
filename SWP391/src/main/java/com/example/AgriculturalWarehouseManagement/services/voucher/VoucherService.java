package com.example.AgriculturalWarehouseManagement.services.voucher;

import com.example.AgriculturalWarehouseManagement.models.DiscountType;
import com.example.AgriculturalWarehouseManagement.models.Voucher;
import com.example.AgriculturalWarehouseManagement.dtos.VoucherDTO;
import com.example.AgriculturalWarehouseManagement.models.VoucherStatus;
import com.example.AgriculturalWarehouseManagement.repositories.OrderRepository;
import com.example.AgriculturalWarehouseManagement.repositories.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
