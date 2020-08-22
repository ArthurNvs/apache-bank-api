package br.com.theapache.apachebank.controllers.dto;

import org.springframework.data.domain.Page;

import br.com.theapache.apachebank.models.Customer;

public class CustomerDTO {
	
	private Long id;
	private String IdNumber;
	private String FirstName;
	//private AccountDTO Account;
	
	
	public CustomerDTO(Customer customer) {
		this.id = customer.getId();
		this.IdNumber = customer.getIdNumber();
		this.FirstName = customer.getFirstName();
		//.Account = new AccountDTO(customer.getAccount());
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getCustomerFirstName() {
		return this.FirstName;
	}
	
	public String getCustomerIdNumber() {
		return this.IdNumber;
	}
	
	/*
	 * public AccountDTO getCustomerAccount() { return this.Account; }
	 */

	public static Page<CustomerDTO> convertToDTO(Page<Customer> customers) {
		return customers.map(CustomerDTO::new);
	}

}