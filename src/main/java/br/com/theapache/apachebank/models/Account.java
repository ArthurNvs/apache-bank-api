package br.com.theapache.apachebank.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private AccountType accountType;
	protected BigDecimal balance = new BigDecimal(0);
	private String number;
	private String branch;
	
	public Account() {
		
	}

	public Account(String number, String branch, BigDecimal balance, AccountType accountType) {
		this.number = number;
		this.branch = branch;
		this.balance = balance;
		this.accountType = accountType;
		
	}

	public void withdraw(BigDecimal value) {
		
		this.accountType.withdraw(this.balance, value);

	}

	public void deposit(BigDecimal value) {
		this.balance.add(value);
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	
	@Override
	public String toString() {
		return "Balance: $" + this.balance + " / " + "Account number: " + this.number + " / " + "Account branch: "
				+ this.branch;
	}
}
