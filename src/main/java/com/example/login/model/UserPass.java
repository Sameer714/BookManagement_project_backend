package com.example.login.model;

public class UserPass {
	private String email;
	private String newPass;
	
	public UserPass(String email, String pass) {
		super();
		this.email = email;
		this.newPass = pass;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String pass) {
		this.newPass = pass;
	}
	
	@Override
	public String toString() {
		return "UserPass [email=" + email + ", pass=" + newPass + "]";
	}
	
	public UserPass() {
		super();
	}
}