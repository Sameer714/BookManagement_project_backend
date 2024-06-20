package com.example.login.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.duplicate.DuplicateUsernameException;
import com.example.login.model.Messg;
import com.example.login.model.Pass;
import com.example.login.model.User;
import com.example.login.model.UserPass;
import com.example.login.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1/api")

public class UserController {

	Messg mssg;

	@Autowired
	UserService userService;

	@GetMapping("/getAllUsers")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public List<User> getAllusers() {
		return userService.getAllusers();
	}

	@GetMapping("/getuserInfo/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public User getUserinfo(@PathVariable long id) {
		return userService.getUserinfo(id);
	}

	@DeleteMapping("/deluser/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void delUser(@PathVariable long id) {
		userService.delUser(id);
	}

	@PostMapping(value = "/createuser", produces = "application/json")
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		try {
			User saveUser = userService.saveUser(user);
			if (saveUser != null) {
				if (saveUser.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
					return ResponseEntity.status(HttpStatus.OK)
							.body("{\"message\": \"" + "User Saved Successfully , Wait for Admin Approval!"
									+ "\" ,  \"Success\": \"" + "true" + "\"}");
				} else if (saveUser.getRole().equalsIgnoreCase("ROLE_USER")) {
					return ResponseEntity.status(HttpStatus.OK).body(
							"{\"message\": \"" + "User Saved Successfully" + "\" ,  \"Success\": \"" + "true" + "\"}");
				}
			}
		} catch (DuplicateUsernameException e) {
			return ResponseEntity.status(HttpStatus.OK)
					.body("{\"message\": \"" + e.getMessage() + "\" , \"Success\": \"" + "false" + "\"}");
		}
		return null;
	}

	@PutMapping("/Updateuser/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> updateUser(@RequestBody User user, @PathVariable long id)
			throws DuplicateUsernameException {
		try {
			User saveUser = userService.updateUser(user, id);
			if (saveUser != null) {
				if (saveUser.getRole().equals("ROLE_ADMIN")) {
					return ResponseEntity.status(HttpStatus.OK)
							.body("{\"message\": \"" + "User Created Successfully , Wait for Admin Approval!"
									+ "\" ,  \"Success\": \"" + "true" + "\"}");
				} else if (saveUser.getRole().equals("ROLE_USER")) {
					return ResponseEntity.status(HttpStatus.OK).body(
							"{\"message\": \"" + "User Saved Successfully" + "\" ,  \"Success\": \"" + "true" + "\"}");
				}
			}
		} catch (DuplicateUsernameException e) {
			return ResponseEntity.status(HttpStatus.OK)
					.body("{\"message\": \"" + e.getMessage() + "\" , \"Success\": \"" + "false" + "\"}");
		}
		return null;
	}
	
	@PostMapping("/changePass")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<String> changepass(@RequestBody Pass pass) {
		return userService.changePass(pass.getEmail(), pass.getOldpass(), pass.getPass());
	}

	@GetMapping("/getAllUserToApprove")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<User> getAllUserToApprove() {
		return userService.getAllUserToApprove();
	}

	@PutMapping("/updateStatus/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> updateStatus(@PathVariable long id) {
		return userService.updateStatus(id);
	}
}