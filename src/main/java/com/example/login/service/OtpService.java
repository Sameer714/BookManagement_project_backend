package com.example.login.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.login.model.CheckOtp;
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

	public ResponseEntity<Object> sendOtp(String email) {
		String otp = OtpService.createOtp();
		User user = userRepo.findByEmail(email);
		if (user != null) {
			Otp oldEntry = otpRepo.findByEmailAndUserName(user.getEmail(), user.getUsername());
			if (oldEntry != null) {
				oldEntry.setEmail(user.getEmail());
				oldEntry.setOtp(Integer.parseInt(otp));
				oldEntry.setUserName(user.getUsername());
				oldEntry.setStatus("ACTIVE");
				otpRepo.save(oldEntry);
				mailService.sendMail(email, oldEntry);
			} else {
				Otp saveOtp = new Otp();
				saveOtp.setEmail(user.getEmail());
				saveOtp.setOtp(Integer.parseInt(otp));
				saveOtp.setUserName(user.getUsername());
				saveOtp.setStatus("ACTIVE");
				otpRepo.save(saveOtp);
				mailService.sendMail(email, saveOtp);
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body("{\"message\": \"" + "OTP sent Successfully" + "\" ,  \"Success\": \"" + "true" + "\"}");
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body("{\"message\": \"" + "The OTP couldn't be sent!" + "\" ,  \"Success\": \"" + "false" + "\"}");
		}
	}

	public static String createOtp() {
		int otp = new Random().nextInt(90000) + 10000;
		return String.valueOf(otp);
	}

	public ResponseEntity<Object> checkAndValidateOtp(CheckOtp checkOtp) {
		if(checkOtp != null && checkOtp.getEmail() != null) {
			Otp entry = otpRepo.findByEmail(checkOtp.getEmail());
			if(checkOtp.getOtp() != null && entry.getStatus().equals("ACTIVE")) {
				if(Integer.parseInt(checkOtp.getOtp()) == (entry.getOtp())) {
					entry.setStatus("INACTIVE");
					otpRepo.save(entry);
					return ResponseEntity.status(HttpStatus.OK)
							.body("{\"message\": \"" + "OTP verified Successfully" + "\" ,  \"Success\": \"" + "true" + "\"}");
				}else {
					return ResponseEntity.status(HttpStatus.OK)
							.body("{\"message\": \"" + "OTP not verified" + "\" ,  \"Success\": \"" + "false" + "\"}");
				}
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body("{\"message\": \"" + "Check you otp and try again" + "\" ,  \"Success\": \"" + "false" + "\"}");
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body("{\"message\": \"" + "Payload is missing" + "\" ,  \"Success\": \"" + "false" + "\"}");
	}
}