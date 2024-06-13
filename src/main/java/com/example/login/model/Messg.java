package com.example.login.model;

public class Messg {
	private String fail ;

	public String getFail() {
		return fail;
	}

	public void setFail(String fail) {
		this.fail = fail;
	}

	public Messg(String fail) {
		super();
		this.fail = fail;
	}

	@Override
	public String toString() {
		return "Messg [fail=" + fail + "]";
	}

	public Messg() {
		super();
		}
}