package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.*;
import com.example.AgriculturalWarehouseManagement.Backend.filters.JwtTokenFilter;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.*;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CheckOutUserController {

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    private UserCustomerService userCustomerService;

    @Autowired
    private AddressBookService addressBookService;

    @Autowired
    private CartUserService cartUserService;

    @Autowired
    private CheckOutUserService checkOutUserService;

    @Autowired
    private VoucherUsersService voucherUsersService;

    @GetMapping("/checkOut")
    public String checkOut(@RequestParam(name = "voucherId", required = false,defaultValue = "0") int voucherId,
                           Model model) {

        String token = (String) session.getAttribute("authToken");

        if (token == null) {
            session.invalidate();
            return "redirect:/login";
        }

        // Giải mã token
        Claims claims = jwtTokenFilter.decodeToken(token);
        if (claims == null) {
            session.invalidate();
            return "redirect:/login";
        }

        // Lấy thông tin người dùng từ claims
        String email = claims.getSubject();
        User userEntity = userCustomerService.loadUserByEmail(email);

        Object account = session.getAttribute("account");
        if (account == null) {
            session.invalidate();
            return "redirect:/login";
        }


        if (userEntity == null) {
            session.invalidate();
            return "redirect:/login";
        } else {

            ResponseResult<List<CartUserResponse>> result = cartUserService.getCartHomeByUserIds(userEntity.getUserId());
            if (result.getData().size() == 0) {
                return "redirect:/cart";
            }

            // View list address
            List<AddressBookResponse> addressBookResponses = addressBookService.getAddressBookByUserId((long) userEntity.getUserId());
            model.addAttribute("addressBookResponses", addressBookResponses);

            // View list cart
            List<CheckOutProductsResponse> checkOutProductsResponses = checkOutUserService.getCheckOutProductDetailByUserIDs(userEntity.getUserId());
            double totalPrice = 0;
            for (CheckOutProductsResponse checkOutProductsResponse : checkOutProductsResponses) {
                totalPrice += checkOutProductsResponse.getTotalPriceProductDetail();
            }
            model.addAttribute("checkOutProductsResponses", checkOutProductsResponses);

            // View Voucher
            List<VoucherUsersResponse> voucherUsersResponses = voucherUsersService.getVoucherUsersOrEmpty();
            List<VoucherUsersResponse> checkVoucherUserResponse = new ArrayList<>();
            for (VoucherUsersResponse voucherUsersResponse : voucherUsersResponses) {
                if (voucherUsersResponse.getMinOrderAmount() <= totalPrice) {
                    if (voucherUsersResponse.getDiscountType().equalsIgnoreCase("PERCENT")) {
                        double percentDiscount = (totalPrice * voucherUsersResponse.getDiscountValue()) / 100;
                        System.out.printf("hello" + percentDiscount);
                        if (percentDiscount <= 200000){
                            checkVoucherUserResponse.add(voucherUsersResponse);
                        }
                    } else if (voucherUsersResponse.getDiscountType().equalsIgnoreCase("AMOUNT")) {
                        checkVoucherUserResponse.add(voucherUsersResponse);
                    }
                }
            }
            model.addAttribute("checkVoucherUserResponse", checkVoucherUserResponse);


            // Add voucher and Check voucherId exist in database
            boolean isCheckedOut = false;
            for (VoucherUsersResponse voucherUsersResponse : checkVoucherUserResponse) {
                if (voucherUsersResponse.getVoucherUserId() == voucherId) {
                    isCheckedOut = true;
                    break;
                }
            }


            if (isCheckedOut) {
                for(VoucherUsersResponse voucherUsersResponse : checkVoucherUserResponse){
                    if (voucherUsersResponse.getVoucherUserId() == voucherId){
                        if (voucherUsersResponse.getDiscountType().equalsIgnoreCase("PERCENT")) {
                            double percentDiscount = (totalPrice * voucherUsersResponse.getDiscountValue()) / 100;
                            model.addAttribute("subTotal", totalPrice);
                            model.addAttribute("percentDiscount", percentDiscount);
                            model.addAttribute("totalPrice", totalPrice - percentDiscount);
                            model.addAttribute("voucherId", voucherUsersResponse.getVoucherUserId());
                        } else if (voucherUsersResponse.getDiscountType().equalsIgnoreCase("AMOUNT")) {
                            model.addAttribute("subTotal", totalPrice);
                            model.addAttribute("percentDiscount", voucherUsersResponse.getDiscountValue());
                            model.addAttribute("totalPrice", totalPrice - voucherUsersResponse.getDiscountValue());
                            model.addAttribute("voucherId", voucherUsersResponse.getVoucherUserId());
                        }
                        break;
                    }
                }
            } else {
                model.addAttribute("totalPrice", totalPrice);
            }

            Object errorMessageCheckOut = session.getAttribute("errorMessageCheckOut");
            if (errorMessageCheckOut != null) {
                model.addAttribute("errorMessageCheckOut", errorMessageCheckOut.toString());
                session.removeAttribute("errorMessageCheckOut");
            }

            Object errorMessageWallets = session.getAttribute("errorMessageWallets");
            if (errorMessageWallets != null) {
                model.addAttribute("errorMessageWallets", errorMessageWallets.toString());
                session.removeAttribute("errorMessageWallets");
            }


            return "FrontEnd/Home/checkout";
        }

    }

    @PostMapping("/addVoucherUser")
    public String addVoucherUser(@RequestParam(name = "voucherId") int voucherId,
            Model model) {
        String token = (String) session.getAttribute("authToken");

        if (token == null) {
            session.invalidate();
            return "redirect:/login";
        }

        // Giải mã token
        Claims claims = jwtTokenFilter.decodeToken(token);
        if (claims == null) {
            session.invalidate();
            return "redirect:/login";
        }

        // Lấy thông tin người dùng từ claims
        String email = claims.getSubject();
        User userEntity = userCustomerService.loadUserByEmail(email);

        if (userEntity == null) {
            session.invalidate();
            return "redirect:/login";
        } else {
            return "redirect:/checkOut?voucherId=" + voucherId;
        }
    }


}
