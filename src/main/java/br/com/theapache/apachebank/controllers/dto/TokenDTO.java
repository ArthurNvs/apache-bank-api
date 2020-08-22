package br.com.theapache.apachebank.controllers.dto;

public class TokenDTO {

	private String type;
	private String token;

	public TokenDTO(String token, String type) {
		this.token = token;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

/*
 * 
 *
 * About "type": It's the HTTP protocol type/ mechanism of authentication that
 * the client side will use to deal with the token. In this case we'll use
 * "Bearer", but another example would be "Digest" or "Basic".
 */