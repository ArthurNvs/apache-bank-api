package br.com.theapache.apachebank.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.theapache.apachebank.models.Customer;
import br.com.theapache.apachebank.repository.CustomerRepository;

//There is no @ notation for Spring recognize this filter, he is added where the Security configurations are!
//You can't use @Autowired here, so the injection must be done through the constructor 
public class AuthenticationViaTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	
	private CustomerRepository customerRepository;
	
	public AuthenticationViaTokenFilter(TokenService tokenService, CustomerRepository customerRepository) {
		this.tokenService = tokenService;
		this.customerRepository = customerRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = retrieveToken(request);		
		boolean valid = tokenService.isTokenValid(token);
		
		if (valid) {
			authenticateClient(token);
		}
			
		filterChain.doFilter(request, response);
	}

	// Take the already authenticated token and re-authenticates by every request
	private void authenticateClient(String token) {
		Long customerId = tokenService.getCustomerId(token);
		Customer customer = customerRepository.findById(customerId).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(customer, null, customer.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}

	private String retrieveToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			
			return null;
		}
		
		return token.substring(7, token.length()); //7 here is the length of "Bearer ".
	}
}

//Authentication here is Stateless so we don't use Sessions