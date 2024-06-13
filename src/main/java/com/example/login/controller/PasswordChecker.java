package com.example.login.controller;

import java.util.regex.*;

public class PasswordChecker {
	private String PASS_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{5,11}$";
	private Pattern pattern = Pattern.compile(PASS_PATTERN);
	public boolean isValid (final String password) {
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
}