package com.example.AgriculturalWarehouseManagement.Backend;

import com.example.AgriculturalWarehouseManagement.dtos.VoucherDTO;
import com.example.AgriculturalWarehouseManagement.models.DiscountType;
import com.example.AgriculturalWarehouseManagement.models.User;
import com.example.AgriculturalWarehouseManagement.models.Voucher;
import com.example.AgriculturalWarehouseManagement.models.VoucherStatus;
import com.example.AgriculturalWarehouseManagement.services.voucher.VoucherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class VoucherController {

    private final VoucherService voucherService;
    private final ModelMapper modelMapper;

    public List<DiscountType> getAllDiscountTypes() {
        return List.of(DiscountType.values());
    }

    @GetMapping("/admin/vouchers")
    public String getAllVouchers(Model model) {
        voucherService.updateAllVoucherStatuses();
        List<Voucher> vouchers = voucherService.findAllSortedByDateOrStatus();
        model.addAttribute("vouchers",vouchers);
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
        return ResponseEntity.ok(Map.of("message", "Voucher unlocked"));
    }
}
