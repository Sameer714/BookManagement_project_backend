package com.example.login.service;

import java.text.DecimalFormat;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.model.Otp;
import com.example.login.model.User;
import com.example.login.repository.OtpRepo;
import com.example.login.repository.UserRepo;

@Service
public class OtpService {
    
    private final UserRepo userRepo;
    private final OtpRepo otpRepo;
    private final MailService mailService;
    
    @Autowired
    public OtpService(UserRepo userRepo, OtpRepo otpRepo, MailService mailService) {
        this.userRepo = userRepo;
        this.otpRepo = otpRepo;
        this.mailService = mailService;
    }
    
    public String sendOtp(String email) {
        String otp = OtpService.createOtp();
        User user = userRepo.findByEmail(email);
        if(user != null) {
            Otp saveOtp = new Otp();
            saveOtp.setEmail(user.getEmail());
            saveOtp.setOtp(Integer.parseInt(otp));
            saveOtp.setUserName(user.getUsername());
            otpRepo.save(saveOtp);
            
            mailService.sendMail(email, saveOtp);
            
            return "otp send Successfully";
        } else {
            return "otp send failed \n please check email id";
        }
    }
    
    public static String createOtp() {
        String otp = new DecimalFormat("000000").format(new Random().nextInt(999999));
        return otp;
    }
}
