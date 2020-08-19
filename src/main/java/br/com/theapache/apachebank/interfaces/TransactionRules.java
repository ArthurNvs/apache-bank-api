package br.com.theapache.apachebank.interfaces;

import java.math.BigDecimal;

public interface TransactionRules {
	
	public void withdraw(BigDecimal balance, BigDecimal value);
	public void deposit(BigDecimal balance, BigDecimal value);
}