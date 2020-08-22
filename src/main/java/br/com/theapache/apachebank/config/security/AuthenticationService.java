package br.com.theapache.apachebank.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.theapache.apachebank.models.Customer;
import br.com.theapache.apachebank.repository.UserProfileRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Customer> customer = userProfileRepository.findByIdNumber(username);

		if (customer.isPresent()) {
			return customer.get();
		}

		throw new UsernameNotFoundException("Invalid data for username");
	}
}

//This class receives all the authentication logic for Spring Security!
//UserDetailsService is responsible for showing Spring Security that authentication logics are here (: