package br.com.theapache.apachebank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.theapache.apachebank.models.Customer;

public interface UserProfileRepository extends JpaRepository<Customer, Long> {
	
	Optional<Customer> findByIdNumber(String idNumber);

}

//About Optional<T>: It can be used to check if there is or there isn't an IdNumber without the need of throwing an exception.
