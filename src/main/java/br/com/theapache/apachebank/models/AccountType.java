package br.com.theapache.apachebank.models;

import java.math.BigDecimal;

import br.com.theapache.apachebank.interfaces.TransactionRules;

public enum AccountType implements TransactionRules {
	STUDENT {
		@Override
		public void withdraw(BigDecimal balance, BigDecimal value) {
			value = value.add(BigDecimal.valueOf(0.13));
			balance.subtract(value);
	
		}

		@Override
		public void deposit(BigDecimal balance, BigDecimal value) {
			
		}
	},

	STANDARD {
		@Override
		public void withdraw(BigDecimal balance, BigDecimal value) {
			value = value.add(BigDecimal.valueOf(0.52));			
			balance.subtract(value);
		}

		@Override
		public void deposit(BigDecimal balance, BigDecimal value) {
			
		}
	},

	PREMIUM {
		@Override
		public void withdraw(BigDecimal balance, BigDecimal value) {
			value = value.add(BigDecimal.valueOf(0.02));			
			balance.subtract(value);
		}

		@Override
		public void deposit(BigDecimal balance, BigDecimal value) {
			
		}
	},;
}
