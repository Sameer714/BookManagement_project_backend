package com.example.login.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Otp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String userName;
	private int otp;
	private String email;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Otp(long id, String userName, int otp, String email) {
		super();
		this.id = id;
		this.userName = userName;
		this.otp = otp;
		this.email = email;
	}

	public Otp() {
		super();
	}

	@Override
	public String toString() {
		return "Otp [id=" + id + ", userName=" + userName + ", otp=" + otp + ", email=" + email + "]";
	}

}
