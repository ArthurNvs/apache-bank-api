package br.com.theapache.apachebank.models;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TransactionData {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	private Account fromAccount;
//	private Account toAccount;
	
	private BigDecimal value;
	private Calendar transactionDate;
	
	public TransactionData(BigDecimal value, Calendar transactionDate, Account fromAccount, Account toAccount) {
		this.value = value;
		this.transactionDate = transactionDate;
//		this.fromAccount = fromAccount;
//		this.toAccount = toAccount;
		
	}
	
	@Override
	public String toString() {
		return transactionDate.getTime().toString() + value;

//		return transactionDate.getTime().toString() + value + fromAccount.getAccountType().toString() + "  -->  " + toAccount.getAccountType().toString();
	}
}
