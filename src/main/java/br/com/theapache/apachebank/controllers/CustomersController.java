package br.com.theapache.apachebank.controllers;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.theapache.apachebank.controllers.dto.CustomerDTO;
import br.com.theapache.apachebank.controllers.form.CustomerForm;
import br.com.theapache.apachebank.models.Customer;
import br.com.theapache.apachebank.repository.AccountRepository;
import br.com.theapache.apachebank.repository.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomersController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	AccountRepository accountRepository;

	// Read methods
	@GetMapping
	@Cacheable(value = "CustomersList")
	public Page<CustomerDTO> listCustomers(@RequestParam(required = false) String param, 
			@PageableDefault(sort = "firstName", direction = Direction.ASC, page = 0, size = 10) Pageable pagination) {
		
		if (param == null) {
			Page<Customer> customers = customerRepository.findAll(pagination);
			return CustomerDTO.convertToDTO(customers);
		} else {
			Page<Customer> customers = customerRepository.findByAccountNumber(param, pagination);
			return CustomerDTO.convertToDTO(customers);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> detailCustomer(@PathVariable Long id) {

		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			return ResponseEntity.ok(new CustomerDTO(customer.get()));
		}

		return ResponseEntity.notFound().build();

	}

	// Write methods (CUD)
	@PostMapping
	@Transactional // it makes Spring commit data automatically to database
	@CacheEvict(value = "CustomersList", allEntries = true)
	public ResponseEntity<CustomerDTO> registerCustomer(@RequestBody @Valid CustomerForm form,
			UriComponentsBuilder uriBuilder) {

		Customer customer = form.convertToCustomer(accountRepository);
		customerRepository.save(customer);

		URI uri = uriBuilder.path("/customers/{id}").buildAndExpand(customer.getId()).toUri();
		return ResponseEntity.created(uri).body(new CustomerDTO(customer));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerForm form) {

		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		if (optionalCustomer.isPresent()) {
			Customer customer = form.update(id, customerRepository);
			return ResponseEntity.ok(new CustomerDTO(customer));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
		
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		if (optionalCustomer.isPresent()) {
			customerRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}
