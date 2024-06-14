package com.example.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.model.SpringMail;
import com.example.login.service.MailService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping ("/mail")

public class MailController {
	
	@Autowired
	private MailService mailService;
	@PostMapping("/sendmail/{mail}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public String sendMail(@PathVariable String mail, @RequestBody SpringMail mails) {
	    mailService.sendMail(mail, mails);
	    return("Mail Sent Successfully!"); 
	}
}