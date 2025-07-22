package com.example.AgriculturalWarehouseManagement.Backend.services;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.MailBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String username;

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendSimpleMessage(MailBody mailBody){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mailBody.to());
        mailMessage.setFrom(username);
        mailMessage.setSubject(mailBody.subject());
        mailMessage.setText(mailBody.text());

        javaMailSender.send(mailMessage);
    }
}
