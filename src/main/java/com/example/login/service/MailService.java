package com.example.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.login.model.SpringMail;

@Service
public class MailService {

	@Value ("$(spring.mail.username)")
	private String fromMail;
	@Autowired
	private JavaMailSender mailSender;
	public void sendMail (String mail , SpringMail mails) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("techvum1437@gmail.com");
		simpleMailMessage.setSubject(mails.getSubject());
		simpleMailMessage.setText(mails.getBody());
		simpleMailMessage.setTo(mail);
		
		mailSender.send(simpleMailMessage);
	}
}