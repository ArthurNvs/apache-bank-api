package br.com.theapache.apachebank.controllers.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {
	
	private String idNumber;
	private String password;
	
	
	public String getIdNumber() {
		return idNumber;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public UsernamePasswordAuthenticationToken convertToUsernamePasswordAuthToken() {
		return new UsernamePasswordAuthenticationToken(idNumber, password);
	}
}