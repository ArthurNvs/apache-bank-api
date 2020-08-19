package br.com.theapache.apachebank.config.validation;

//Supplies data to ValidationErrorHandler
public class FormErrorDTO {
	
	private String field;
	private String error;
	
	
	public FormErrorDTO(String field, String error) {
		this.field = field;
		this.error = error;
	}


	public String getField() {
		return field;
	}


	public String getMessage() {
		return error;
	}

}
