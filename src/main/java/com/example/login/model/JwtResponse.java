package com.example.login.model;

public class JwtResponse {
	
	private String Jwtoken;
	private String usernm;
	private String role;
	private long id;
	
	public String getJwtoken() {
		return Jwtoken;
	}
	public void setJwtoken(String jwtoken) {
		Jwtoken = jwtoken;
	}
	public String getUsernm() {
		return usernm;
	}
	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public JwtResponse(String jwtoken, String usernm, String role, long id) {
		super();
		Jwtoken = jwtoken;
		this.usernm = usernm;
		this.role = role;
		this.id = id;
	}
	
	public JwtResponse() {
		super();
	}
	@Override
	public String toString() {
		return "JwtResponse [Jwtoken=" + Jwtoken + ", usernm=" + usernm + ", role=" + role + ", id=" + id + "]";
	}
}