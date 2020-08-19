package br.com.theapache.apachebank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.theapache.apachebank.models.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	List<Account> findByNumber(String AccountNumber);
	
	Optional<Account> findById(Long id);
}
