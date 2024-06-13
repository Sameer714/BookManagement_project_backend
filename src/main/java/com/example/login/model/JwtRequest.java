package com.example.login.model;

public class JwtRequest {
	private String gmail;
	private String passw;
	
	public JwtRequest() {
	}
	
	public JwtRequest(String gmail, String passw) {
		super();
		this.gmail = gmail;
		this.passw = passw;
	}
	
	public String getGmail() {
		return gmail;
	}
	
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	
	public String getPassw() {
		return passw;
	}
	
	public void setPassw(String passw) {
		this.passw = passw;
	}
	
	@Override
	public String toString() {
		return "JwtRequest [gmail=" + gmail + ", passw=" + passw + "]";
	}
}