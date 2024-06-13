package com.example.login.model;

public class Pass {
		private String oldpass;
		private String pass;
		private String email;
		
		@Override
		public String toString() {
			return "Pass [oldpass=" + oldpass + ", pass=" + pass + ", email=" + email + "]";
		}
		
		public String getOldpass() {
			return oldpass;
		}
		public void setOldpass(String oldpass) {
			this.oldpass = oldpass;
		}
		public String getPass() {
			return pass;
		}
		public Pass(String oldpass, String pass, String email) {
			super();
			this.oldpass = oldpass;
			this.pass = pass;
			this.email = email;
		}

		public void setPass(String pass) {
			this.pass = pass;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		
}