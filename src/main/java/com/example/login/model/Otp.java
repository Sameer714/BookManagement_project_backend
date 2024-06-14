package com.example.login.model;

public class Otp {
	private long id;
	private long userId;
	private int otp;
	private String email;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Otp(long id, long userId, int otp, String email) {
		super();
		this.id = id;
		this.userId = userId;
		this.otp = otp;
		this.email = email;
	}
	
	public Otp() {
		super();
	}
	
	@Override
	public String toString() {
		return "Otp [id=" + id + ", userId=" + userId + ", otp=" + otp + ", email=" + email + "]";
	}
	
	
	

}
