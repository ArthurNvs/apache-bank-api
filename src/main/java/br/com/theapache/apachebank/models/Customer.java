package br.com.theapache.apachebank.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Customer {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	private String occupation;
	
	@OneToOne
	private Account account;
	
	public Customer() {
		
	}

	public Customer(String firstName, String lastName, String idNumber, String address, String occupation, Account account) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.occupation = occupation;
		this.account = account;
	}


	public Long getId() {
		return this.id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Account getAccount() {
		return this.account;
	}

	public void generateAccount(String number, String branch, BigDecimal balance, AccountType accountType) {
		Account generatedAccount = new Account(number, branch, balance, accountType);
		
		this.account = generatedAccount;
		
		generatedAccount = null;
	}

	public void changeAccountType(AccountType accountType) {
		
		if (account != null)
		account.setAccountType(accountType);
	}

	@Override
	public String toString() {
		return this.firstName + " " + this.occupation;
	}
}