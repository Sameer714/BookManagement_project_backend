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
		if (user != null) {
			Otp saveOtp = new Otp();
			saveOtp.setEmail(user.getEmail());
			saveOtp.setOtp(Integer.parseInt(otp));
			saveOtp.setUserName(user.getUsername());
			otpRepo.save(saveOtp);

			mailService.sendMail(email, saveOtp);

			return "OTP sent Successfully!!";
		} else {
			return "The OTP couldn't be sent.\nPlease check Email!";
		}
	}

	public static String createOtp() {
		int otp = new Random().nextInt(90000) + 10000;
		return String.valueOf(otp);
	}
}
