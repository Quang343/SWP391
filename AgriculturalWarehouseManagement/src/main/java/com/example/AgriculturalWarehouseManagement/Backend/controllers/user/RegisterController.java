package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.components.GenerateOTP;
import com.example.AgriculturalWarehouseManagement.Backend.components.SendVerificationEmail;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.RegisterRequestDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    @Autowired
    private GenerateOTP generateOTPCheck;

    @Autowired
    private SendVerificationEmail sendVerificationEmailCheck;

    @GetMapping("/register")
    public String register() {
        return "FrontEnd/Home/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequestDTO registerRequestDTO, Model model) {

        ResponseResult<User> result = userService.registerUserService(registerRequestDTO);

        if (result.isActive()) {

            session.setAttribute("registerRequestDTO", registerRequestDTO);

            String otp = generateOTPCheck.generateOTP();
            session.setAttribute("generateOTP", otp);

            session.setAttribute("emailRegister", registerRequestDTO.getEmail());

            sendVerificationEmailCheck.sendVerificationEmail(registerRequestDTO.getEmail(),otp);
            return "redirect:/otp";
        } else {
            model.addAttribute("errorRegister", result.getMessage());
            return "FrontEnd/Home/register";
        }
    }

    @GetMapping("/otp")
    public String otp() {
        return "FrontEnd/Home/otp";
    }

    @PostMapping("/otp")
    public String otp(@RequestParam("otp1") String otp1,
                      @RequestParam("otp2") String otp2,
                      @RequestParam("otp3") String otp3,
                      @RequestParam("otp4") String otp4,
                      @RequestParam("otp5") String otp5,
                      @RequestParam("otp6") String otp6, Model model) {

        String otp = otp1 + otp2 + otp3 + otp4 + otp5 + otp6;
        String sessionOtp = (String) session.getAttribute("generateOTP");

        if (otp.equals(sessionOtp)) {
            RegisterRequestDTO registerRequestDTO = (RegisterRequestDTO) session.getAttribute("registerRequestDTO");
            userService.insertUserService(registerRequestDTO, sessionOtp);
            session.removeAttribute("generateOTP");
            session.removeAttribute("registerRequestDTO");
            return "redirect:/login";
        } else {
            model.addAttribute("errorResendOtpRegister", "Error OTP");
            return "FrontEnd/Home/otp";
        }
    }

    @PostMapping("/resendOtpRegister")
    public String resendOtpRegister(Model model) {
        String email = (String) session.getAttribute("emailRegister");
        if (email != null) {

            String otp = generateOTPCheck.generateOTP();
            session.setAttribute("generateOTP", otp);
            sendVerificationEmailCheck.sendVerificationEmail(email, otp);

            return "redirect:/otp";
        } else {
            model.addAttribute("errorResendOtpRegister", "Email not exist !!!");
            return "FrontEnd/Home/otp";
        }
    }

}
