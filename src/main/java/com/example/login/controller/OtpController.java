package com.example.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.model.SpringMail;
import com.example.login.service.OtpService;

@RestController
@RequestMapping("v1/api/otp")
public class OtpController {

	private final OtpService otpService;

	@Autowired
	public OtpController(OtpService otpService) {
		this.otpService = otpService;
	}

	@PostMapping("/send")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public String sendOtp(@RequestBody SpringMail email) {
		return otpService.sendOtp(email.getEmail());
	}
}
