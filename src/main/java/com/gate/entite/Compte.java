package com.gate.entite;

public class Compte {
	
	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Compte(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public Compte() {
		super();
	}
	

}
