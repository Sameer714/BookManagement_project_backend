package com.example.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.model.JwtRequest;
import com.example.login.model.JwtResponse;
import com.example.login.model.User;
import com.example.login.repository.UserRepo;
import com.example.login.security.JwtHelper;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class AuthController {
	private String token;

	public AuthController() {
	}

	public AuthController(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Autowired
	private UserRepo userRepo;
	
//	@Autowired
//	PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
		JwtResponse response = new JwtResponse();

		User user = userRepo.findByEmail(request.getGmail());
		if (user != null && user.getStatus().equalsIgnoreCase("ACTIVE")) {
//			if (passwordEncoder.matches(request.getPassw(), user.getPassword())) {
			if (user.getPassword().equals(request.getPassw())) {

				JwtHelper jwtHelper = new JwtHelper();
				token = jwtHelper.generateToken(user);

				response.setJwtoken(token);
				response.setUsernm(user.getUsername());
				response.setRole(user.getRole());
				response.setId(user.getId());
			} else {
				response.setUsernm(user.getUsername());
			}
		} else {
			response.setUsernm(request.getGmail());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		if (user.getStatus().equalsIgnoreCase("ACTIVE")) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setUsernm("USER INACTIVE!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
}