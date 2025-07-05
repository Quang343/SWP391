package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.VoucherDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.DiscountType;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.models.Voucher;
import com.example.AgriculturalWarehouseManagement.Backend.models.VoucherStatus;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.voucher.VoucherService;
import com.example.AgriculturalWarehouseManagement.Backend.utils.PaginationUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.*;

@RequiredArgsConstructor
@Controller
public class VoucherController {

    private final VoucherService voucherService;
    private final ModelMapper modelMapper;

    public List<DiscountType> getAllDiscountTypes() {
        return List.of(DiscountType.values());
    }

    @GetMapping("/admin/vouchers")
    public String getAllVouchers(Model model,
                                 @RequestParam("page") Optional<String> page){
        voucherService.updateAllVoucherStatuses();
        int pageNumber = 1;
        try{
            if (page.isPresent()) {
                pageNumber = Integer.parseInt(page.get());
                if(pageNumber < 1){
                    pageNumber = 1;
                }
            }
            else{
                //pageNumber = 1
            }
        }catch (Exception e){
            //pageNumber = 1
            //Handle exception
        }

        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        Page<Voucher> pageVoucher = voucherService.findAll(pageable);
        List<Voucher> vouchers = pageVoucher.getContent();
        int totalPages = pageVoucher.getTotalPages();
        if(pageNumber > totalPages){
            pageNumber = 1;
            pageVoucher = voucherService.findAll(PageRequest.of(0, 5));
            vouchers = pageVoucher.getContent();
        }
        model.addAttribute("vouchers", vouchers);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("pageNumbers", PaginationUtils.generatePageNumbers(totalPages, pageNumber));
        model.addAttribute("totalPages", totalPages);
        return "BackEnd/Admin/All_Coupons";
    }

//    @GetMapping("/admin/vouchers")
//    public ResponseEntity<?> getAllVouchers() {
//        voucherService.updateAllVoucherStatuses();
//        List<Voucher> vouchers = voucherService.findAllSortedByDateOrStatus();
//        return ResponseEntity.ok().body(vouchers);
//    }


    @GetMapping("/admin/add_coupon")
    public String addNewCoupon(Model model){
        model.addAttribute("types", getAllDiscountTypes());
        model.addAttribute("voucherDTO",new VoucherDTO());
        return "BackEnd/Admin/Add_Coupon";
    }

//    @PostMapping("/admin/saveCoupon")
//    public String saveCoupon(@Valid @ModelAttribute VoucherDTO voucherDTO,
//                             BindingResult results,
//                             RedirectAttributes redirectAttributes,
//                             Model model){
//        if(results.hasErrors()){
//            model.addAttribute("types", getAllDiscountTypes());
//            return "BackEnd/Admin/Add_Coupon";
//        }
//
//        if(voucherService.existsByVoucherCode(voucherDTO.getVoucherCode())){
//            results.rejectValue("voucherCode", "error.voucherCode", "Voucher code already exists");
//            model.addAttribute("types", getAllDiscountTypes());
//            return "BackEnd/Admin/Add_Coupon";
//        }
//        try {
//            Voucher voucher = voucherService.createVoucher(voucherDTO);
//            redirectAttributes.addFlashAttribute("successMessage", "Coupon added successfully!");
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add coupon: " + e.getMessage());
//        }
//        return "redirect:/admin/vouchers";
//    }

    @PostMapping("/admin/saveCoupon")
    public ResponseEntity<?> saveCoupon(@Valid @ModelAttribute VoucherDTO voucherDTO,
                                        BindingResult results,
                                        RedirectAttributes redirectAttributes,
                                        Model model){
        if(results.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            results.getFieldErrors().forEach((fieldError) -> {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            Voucher voucher = voucherService.createVoucher(voucherDTO);
            return ResponseEntity.ok("Added coupon");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/admin/edit_coupon/{id}")
    public String editCoupon(@PathVariable("id") Long id, Model model){
        Voucher voucher = voucherService.findById(id);
        VoucherDTO voucherDTO = config(voucher);
//        voucherDTO.setStartDate(voucher.getStartDate().toLocalDateTime());
//        voucherDTO.setEndDate(voucher.getEndDate().toLocalDateTime());
        model.addAttribute("voucherId", id);
        model.addAttribute("voucherDTO", voucherDTO);
        model.addAttribute("types", getAllDiscountTypes());
        return "BackEnd/Admin/Edit_Coupon";
    }

    private VoucherDTO config(Voucher voucher){
        modelMapper.typeMap(Voucher.class, VoucherDTO.class);
        return modelMapper.map(voucher, VoucherDTO.class);
    }

    @PostMapping("/admin/update_coupon/{id}")
    public ResponseEntity<?> updateCoupon(@PathVariable("id") Long id,
                                          @Valid VoucherDTO voucherDTO,
                                          BindingResult results){
        if(results.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            results.getFieldErrors().forEach((fieldError) -> {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }

        if(voucherDTO.getDiscountType().equals(DiscountType.PERCENT.name())){
            BigDecimal discountValue = voucherDTO.getDiscountValue();
            if(discountValue.intValue() < 0 || discountValue.intValue() > 100){
                return ResponseEntity.badRequest().body(Map.of("discountValue", "Invalid discount value!"));
            }
        }

        try {
            Voucher voucher = voucherService.updateVoucher(id, voucherDTO);
            return ResponseEntity.ok("Updated coupon");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/admin/voucher/delete/{id}")
    public ResponseEntity<?> deleteVoucher(@PathVariable("id") Long id){
        String message = voucherService.deleteVoucher(id);
        return ResponseEntity.ok(Map.of("message", message));
    }

    @PatchMapping("/admin/voucher/unlock/{id}")
    public ResponseEntity<?> unlockVoucher(@PathVariable("id") Long id){
        voucherService.unlockVoucher(id);
        return ResponseEntity.ok(Map.of("message", "Voucher đã được mở khoá"));
    }
}
