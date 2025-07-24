package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.MailBody;
import com.example.AgriculturalWarehouseManagement.Backend.models.ForgotPassword;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ForgotPasswordRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.UserRepository;
import com.example.AgriculturalWarehouseManagement.Backend.services.EmailService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.user.UserService;
import com.example.AgriculturalWarehouseManagement.Backend.utils.ChangePassword;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.*;

@Controller
//@RestController
@RequestMapping("/forgotPasswordAdmin")
public class ForgotPasswordAdminController {

    private final ForgotPasswordRepository forgotPasswordRepository;
    private final UserService userService;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public ForgotPasswordAdminController(UserRepository userRepository,
                                         ForgotPasswordRepository forgotPasswordRepository,
                                         UserService userService,
                                         EmailService emailService,
                                         PasswordEncoder passwordEncoder) {
        this.forgotPasswordRepository = forgotPasswordRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("")
    public String getForgotPasswordPage(){
        return "BackEnd/Admin/Forgot_Password";
    }

    @GetMapping("/enter-otp")
    public String enterOtp(HttpSession session){
        String email = (String) session.getAttribute("resetEmail");
        Boolean emailVerified = (Boolean) session.getAttribute("emailVerified");

        if (email == null || emailVerified == null || !emailVerified) {
            session.removeAttribute("resetEmail");
            session.removeAttribute("emailVerified");
            session.removeAttribute("otpVerified");
            return "redirect:/login";
        }

        return "BackEnd/Admin/EnterOTP";
    }

    //send email for email verification
    @PostMapping("/verify/{email}")
    public ResponseEntity<?> verifyEmail(@PathVariable String email, HttpSession session){
        sendOtpToEmail(email);
        session.setAttribute("resetEmail", email);
        session.setAttribute("emailVerified", true);

        return ResponseEntity.ok(Map.of("success", "Email send for verification!"));
    }

    @PostMapping("/resendOtp")
    public ResponseEntity<?> resendEmail(HttpSession session ){
        String email = (String)session.getAttribute("resetEmail");

        if(email == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Email chưa được xác nhận trước đó."));
        }
        sendOtpToEmail(email);
        return ResponseEntity.ok(Map.of("success", "OTP đã được gửi lại!"));
    }

    private void sendOtpToEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email không hợp lệ!"));

        //xoá nếu trước đó có bản ghi cũ
        forgotPasswordRepository.deleteByUser((long)user.getUserId());

        int otp = otpGenerator();
        MailBody mailBody = MailBody.builder()
                .to(email)
                .subject("OTP for Forgot Password request")
                .text("This is the OTP for your Forgot Password Request: " + otp)
                .build();

        ForgotPassword fp = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 120 * 1000))
                .user(user)
                .build();

        emailService.sendSimpleMessage(mailBody);
        forgotPasswordRepository.save(fp);
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<?> verifyOTP(@RequestParam("otp") Integer otp,
                                       @RequestParam("email") String email,
                                       HttpSession session){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please provide a valid email!"));

        ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(otp, user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid OTP for email!"));


        if(fp.getExpirationTime().before(Date.from(Instant.now()))){
            forgotPasswordRepository.deleteById(fp.getFpid());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã OTP của bạn đã hết hạn!");
        }
        session.setAttribute("otpVerified", true);
        forgotPasswordRepository.deleteById(fp.getFpid());
        return ResponseEntity.ok("OTP verified");
    }

    @GetMapping("/resetPasswordForm")
    public String changePasswordForm(HttpSession session){
        String email = (String) session.getAttribute("resetEmail");
        Boolean otpVerified = (Boolean) session.getAttribute("otpVerified");

        if (email == null || otpVerified == null || !otpVerified) {
            session.removeAttribute("resetEmail");
            session.removeAttribute("emailVerified");
            session.removeAttribute("otpVerified");
            return "redirect:/login";
        }

        return "BackEnd/Admin/Reset_Password";
    }

    @PostMapping("/changePassword/{email}")
    public  ResponseEntity<?> changePasswordHandler(@RequestBody ChangePassword changePassword,
                                                    @PathVariable String email,
                                                    HttpSession session){
        if(!Objects.equals(changePassword.password(), changePassword.rePassword())){
            return new ResponseEntity<>("Please enter password again!", HttpStatus.EXPECTATION_FAILED);
        }

        String encodedPassword = passwordEncoder.encode(changePassword.password());
//        User user = userService.findByEmail(email);
//        user.setPassword(encodedPassword);
//        userRepository.save(user);
        userRepository.updateUser(email, encodedPassword);
        session.removeAttribute("resetEmail");
        session.removeAttribute("emailVerified");
        session.removeAttribute("otpVerified");
        return new ResponseEntity<>("Password has been changed.", HttpStatus.OK);
    }

    private Integer otpGenerator(){
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(Map.of("error", ex.getReason()));
    }
}
