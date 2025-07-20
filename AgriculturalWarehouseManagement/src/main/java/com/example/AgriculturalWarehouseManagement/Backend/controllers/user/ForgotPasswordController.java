package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.components.GenerateOTP;
import com.example.AgriculturalWarehouseManagement.Backend.components.SendVerificationEmail;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.UserCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForgotPasswordController {

    @Autowired
    private UserCustomerService userCustomerService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private GenerateOTP generateOTPCheck;

    @Autowired
    private SendVerificationEmail sendVerificationEmailCheck;

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    @GetMapping("/forgotPassword")
    public String forgotPassword() {
        return "FrontEnd/Home/forgot";
    }

    @PostMapping("/forgotPassword")
    public String forgotPasswordSubmit(@ModelAttribute("email") String email, Model model) {

        User user = userCustomerService.loadUserByEmail(email);

        if (user != null) {
            if (user.getStatusGG().equals("Active")) {
                model.addAttribute("errorForgotpassword", "Email đã có trong Google !!!");
                return "FrontEnd/Home/forgot";
            } else {
                String otpForgotPassword = generateOTPCheck.generateOTP();
                session.setAttribute("otpForgotPassword", otpForgotPassword);
                session.setAttribute("emailOtp", email);

                sendVerificationEmailCheck.sendVerificationEmail(user.getEmail(), otpForgotPassword);
                return "redirect:/otpForgotPassword";
            }
        } else {
            model.addAttribute("errorForgotpassword", "Email không tồn tại !!!");
            return "FrontEnd/Home/forgot";
        }


    }

    @PostMapping("/resendOtp")
    public String forgotPasswordSubmit(Model model) {

        String email = (String) session.getAttribute("emailOtp");
        if (email != null) {
            String otpForgotPassword = generateOTPCheck.generateOTP();
            session.setAttribute("otpForgotPassword", otpForgotPassword);
            sendVerificationEmailCheck.sendVerificationEmail(email, otpForgotPassword);

            return "redirect:/otpForgotPassword";
        } else {
            model.addAttribute("errorResendOtp", "Email không tồn tại !!!");
            return "FrontEnd/Home/otpForgotPassword";
        }

    }

    @GetMapping("/otpForgotPassword")
    public String otpForgoPassword() {
        return "FrontEnd/Home/otpForgotPassword";
    }

    @PostMapping("/otpForgotPassword")
    public String otp(@RequestParam("otp1") String otp1,
                      @RequestParam("otp2") String otp2,
                      @RequestParam("otp3") String otp3,
                      @RequestParam("otp4") String otp4,
                      @RequestParam("otp5") String otp5,
                      @RequestParam("otp6") String otp6, Model model) {

        String otp = otp1 + otp2 + otp3 + otp4 + otp5 + otp6;
        String sessionOtp = (String) session.getAttribute("otpForgotPassword");

        if (!otp.equals(sessionOtp)) {
            model.addAttribute("errorOtpForgotpassword", "OTP không hợp lệ");
            return "FrontEnd/Home/otpForgotPassword";
        } else {
            return "redirect:/changeForgotPassword";
        }

    }

    @GetMapping("/changeForgotPassword")
    public String changeForgotPassword() {
        return "FrontEnd/Home/changeForgotPassword";
    }

    @PostMapping("/changeForgotPassword")
    public String changeForgotPasswordSubmit(@ModelAttribute("changePassword") String changePassword, Model model) {
        String email = (String) session.getAttribute("emailOtp");
        ResponseResult<User> messageChangePassword = userCustomerService.changePassword(email, changePassword);

        if (messageChangePassword.getMessage() == null || messageChangePassword.getMessage().equals("")) {
            model.addAttribute("errorChangePassword", "Không tìm thấy email");
            return "FrontEnd/Home/changeForgotPassword";
        } else {
            if (!messageChangePassword.isActive()) {
                model.addAttribute("errorChangePassword", messageChangePassword.getMessage());
                return "FrontEnd/Home/changeForgotPassword";
            } else {
                model.addAttribute("successChangePassword", messageChangePassword.getMessage());
                return "FrontEnd/Home/forgot";
            }

        }

    }

}
