package com.example.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.login.controller.PasswordChecker;
import com.example.login.duplicate.DuplicateUsernameException;
import com.example.login.model.User;
import com.example.login.repository.UserRepo;

@Service
@CrossOrigin(origins = "http://localhost:4200/")

public class UserService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	public User getUserinfo(long id) {
		User a = userRepo.findById(id);
		return a;
	}

	public void delUser(long id) {
		User a = userRepo.findById(id);
		userRepo.delete(a);
	}

	public User saveUser(User user) throws DuplicateUsernameException {
		User isUserPresent = userRepo.findByEmail(user.getEmail());
		if (isUserPresent == null) {
			User u = new User();
			u.setName(user.getName());
			User isusrnmPresent = userRepo.findByUserName(user.getUsername());
			if (isusrnmPresent == null) {
				u.setUserName(user.getUsername());
			} else {
				throw new DuplicateUsernameException("Username Already Exists", "Duplicate :");
			}
			if (user.getRole().equalsIgnoreCase("ADMIN")) {
				u.setStatus("INACTIVE");
			} else {
				u.setStatus("ACTIVE");
			}
			u.setEmail(user.getEmail());
			u.setRole("ROLE_" + user.getRole());
			if (user.getPassword().isEmpty()) {
				throw new DuplicateUsernameException("Enter Password!", "Invalid : ");

			} else {
				PasswordChecker passwordChecker = new PasswordChecker();
				if (passwordChecker.isValid(user.getPassword())) {
					u.setPass(user.getPassword());
					return userRepo.save(u);
				} else {
					throw new DuplicateUsernameException("Password doesn't follow our criteria!", "Invalid : ");
				}
			}
		}
		throw new DuplicateUsernameException("Email id Already Registered!", "Duplicate :");
	}

	public User updateUser(User user, long id) throws DuplicateUsernameException {
		User u = userRepo.findById(id);
		u.setName(user.getName());
		User isUserPresent = userRepo.findByEmail(user.getEmail());
		if (isUserPresent == null) {
			User isusrnmPresent = userRepo.findByUserName(user.getUsername());
			if (isusrnmPresent == null) {
				u.setUserName(user.getUsername());
			} else {
				throw new DuplicateUsernameException("Username Already Exists", "Duplicate :");
			}
			u.setEmail(user.getEmail());
			u.setRole("ROLE_" + user.getRole());
			if (user.getPassword().isEmpty()) {
				throw new DuplicateUsernameException("Enter Password!", "Invalid : ");
			} else {
				PasswordChecker passwordChecker = new PasswordChecker();
				if (passwordChecker.isValid(user.getPassword())) {
					u.setPass(user.getPassword());
					return userRepo.save(u);
				} else {
					throw new DuplicateUsernameException("Password doesn't follow our criteria!", "Invalid : ");
				}
			}
		}
		throw new DuplicateUsernameException("Email id Already Registered!", "Duplicate :");
	}

	public User loadUserByUsername(String userName) {
		User user = userRepo.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("User couldn't be found");
		} else {
			return user;
		}
	}

	public List<User> getAllusers() {
		return userRepo.findAll();
	}

	public List<User> getAllUserToApprove() {
		return userRepo.findAllByStatusAndRole("INACTIVE", "ROLE_ADMIN");
	}

	public ResponseEntity<String> updateStatus(long id) {
		User user = userRepo.findById(id);
		if (user.getStatus().equalsIgnoreCase("INACTIVE")) {
			user.setStatus("ACTIVE");
			userRepo.save(user);
			return ResponseEntity.status(HttpStatus.OK)
					.body("{\"message\": \"" + "Updated Successfully" + "\" ,  \"Success\": \"" + "true" + "\"}");
		} else {
			user.setStatus("INACTIVE");
			userRepo.save(user);
			return ResponseEntity.status(HttpStatus.OK)
					.body("{\"message\": \"" + "Updated Successfully" + "\" ,  \"Success\": \"" + "true" + "\"}");
		}
	}

	public ResponseEntity<String> changePass (String email, String old ,String Password) {
		
		User user = userRepo.findByEmail(email);
		if (user == null) {
			return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \""
					+ "Email Doesnt Exist! , Create an Account now!" + "\" ,  \"Success\": \"" + "false" + "\"}");
		}
		if (user.getStatus().equalsIgnoreCase("INACTIVE")) {
			return ResponseEntity.status(HttpStatus.OK)
					.body("{\"message\": \"" + "Email Inactive!" + "\" ,  \"Success\": \"" + "false" + "\"}");
		} else {
			if(user.getPassword().equals(old)) {
				user.setPass(Password);
				PasswordChecker passwordChecker = new PasswordChecker();
				if (passwordChecker.isValid(user.getPassword())) {
					user.setPass(user.getPassword());
					userRepo.save(user);
					return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"" + "Password Updated Successfully!"
							+ "\" ,  \"Success\": \"" + "true" + "\"}");
				} else {
					return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \""
							+ "Password Doesnt Follow Our Criteria!" + "\" ,  \"Success\": \"" + "false" + "\"}");
				}
				
			}
			else {
				return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \""
						+ "Old Password is not matching!" + "\" ,  \"Success\": \"" + "false" + "\"}");
			}
		}
	}
}