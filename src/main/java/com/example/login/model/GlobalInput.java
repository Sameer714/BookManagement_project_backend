package com.example.login.model;

public class GlobalInput {

	public static class CheckOtp {

		private String email;
		private String otp;
		private String newPass;
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getOtp() {
			return otp;
		}
		public void setOtp(String otp) {
			this.otp = otp;
		}
		public String getNewPass() {
			return newPass;
		}
		public void setNewPass(String newPass) {
			this.newPass = newPass;
		}
		
	}
}
