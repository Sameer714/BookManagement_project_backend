package com.example.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.login.model.Otp;

@Service
public class MailService {

    private final JavaMailSender mailSender;
    
    @Value("${spring.mail.username}")
    private String fromMail;

    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String mail, Otp otp) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setSubject("OTP for password reset");
        simpleMailMessage.setText("UserName " + otp.getUserName() + " Your otp for Password Reset is " + otp.getOtp());
        simpleMailMessage.setTo(otp.getEmail());

        mailSender.send(simpleMailMessage);
    }
}
