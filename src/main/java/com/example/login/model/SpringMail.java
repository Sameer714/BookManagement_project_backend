package com.example.login.model;

public class SpringMail {
private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public SpringMail( String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "SpringMail [ email=" + email + "]";
	}
	
	public SpringMail() {
		super();
	}
}
