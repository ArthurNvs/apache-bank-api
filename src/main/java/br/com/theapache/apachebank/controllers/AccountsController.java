package br.com.theapache.apachebank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.theapache.apachebank.controllers.dto.AccountDTO;
import br.com.theapache.apachebank.models.Account;
import br.com.theapache.apachebank.repository.AccountRepository;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

	@Autowired
	AccountRepository accountRepository;

	@GetMapping
	public List<AccountDTO> listAccounts(String param) {
		if (param == null) {
			List<Account> accounts = accountRepository.findAll();
			return AccountDTO.convert(accounts);
		} else {
			List<Account> accounts = accountRepository.findByNumber(param);
			return AccountDTO.convert(accounts);
		}
	}
	
	@PostMapping
	public void createAccount() {
		//@TODO
		//generate account with random number and 3 branch options
		//Remember to get the account id and set it to the customer accountId
		
	}
}