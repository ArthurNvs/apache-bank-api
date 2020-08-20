package br.com.theapache.apachebank.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.theapache.apachebank.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Page<Customer> findByAccountNumber(String accountNumber, Pageable pagination);

}

//This interface inherits from  JpaRepository<Type Class, ID type>, which can get any value from the Type Class from the method findBy"VarName".
//Note that "findById();" is a default implementation from Spring Boot, while "findByAccountNumber();" is a custom implementation.