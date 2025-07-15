package com.example.AgriculturalWarehouseManagement.Backend.components;


import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendVerificationEmail {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String toEmail, String verificationCode) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            String subject = "Mã xác minh";
            String body = "Mã xác minh của bạn là: " + verificationCode + "\nHãy nhập mã này để thay đổi mật khẩu của bạn.";

            helper.setFrom("your-email@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body);

            mailSender.send(message);

            System.out.println("Mã xác minh đã được gửi đến email: " + toEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

