package com.example.login.model;

public class SpringMail {
//	private String email;
	
//	private int userId;
	
	private String body;
	private String subject;
	
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public int getUserId() {
//		return userId;
//	}
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public SpringMail(String body, String subject) {
		super();
//		this.email = email;
//		this.userId = userId;
		this.body = body;
		this.subject = subject;
	}
	
	@Override
	public String toString() {
		return "SpringMail [body=" + body + ", subject=" + subject + "]";
	}
	
	public SpringMail() {
		super();
	}
}
