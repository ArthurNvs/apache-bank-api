package br.com.theapache.apachebank.controllers.form;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import br.com.theapache.apachebank.models.Customer;
import br.com.theapache.apachebank.repository.AccountRepository;
import br.com.theapache.apachebank.repository.CustomerRepository;

public class CustomerForm {

	@NotNull @NotEmpty @Length(min = 2)
	private String firstName;
	@NotNull @NotEmpty @Length(min = 1)
	private String lastName;
	@NotNull @NotEmpty 
	private String idNumber;
	@NotNull @NotEmpty 
	private String address;
	@NotNull @NotEmpty
	private String occupation;
	@Nullable
	private Long accountId;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Customer convertToCustomer(AccountRepository accountRepository) {	
		
//		Optional<Account> optionalAccount = accountRepository.findById(getAccountId());
//		Account account = optionalAccount.get();
		
		return new Customer(firstName, lastName, idNumber, address, occupation);
	}

	public Customer update(Long id, CustomerRepository customerRepository) {
		Customer customer = customerRepository.getOne(id);
		customer.setAddress(this.address);
		customer.setFirstName(this.firstName);
		customer.setLastName(this.lastName);
		customer.setOccupation(this.occupation);
		customer.setIdNumber(this.idNumber);
		
		return customer;
	}
}