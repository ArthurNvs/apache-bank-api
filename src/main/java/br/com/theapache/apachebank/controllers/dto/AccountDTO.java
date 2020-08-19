package br.com.theapache.apachebank.controllers.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.theapache.apachebank.models.Account;
import br.com.theapache.apachebank.models.AccountType;

public class AccountDTO {
	
	private AccountType accountType;
	private BigDecimal balance;
	
	public AccountDTO (Account account) {
		this.balance = account.getBalance();
		this.accountType = account.getAccountType();
		
	}

	public BigDecimal getBalance() {
		return this.balance;
	} 
	
	public AccountType getAccountType() {
		return accountType;
	}
	
	public static List<AccountDTO> convert(List<Account> accounts) {
		return accounts.stream().map(AccountDTO::new).collect(Collectors.toList());
		//seria um for na lista de contas, daria um new DTO pra cada conta da lista, guardaria eles numa lista de DTO e retornaria essa lista no final.
	}
}
