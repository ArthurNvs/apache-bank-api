package br.com.theapache.apachebank.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.theapache.apachebank.repository.CustomerRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {

		return super.authenticationManager();
	}
	
	//Authentication configurations
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//URLs Authorization configurations
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers(HttpMethod.GET,"/").permitAll().and().authorizeRequests()
				.antMatchers("/console/**").permitAll()
				.antMatchers(HttpMethod.POST, "/auth/**").permitAll()
				.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
				.anyRequest().authenticated()
				.and().csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilterBefore(new AuthenticationViaTokenFilter(tokenService, customerRepository), UsernamePasswordAuthenticationFilter.class); //tells Spring not to create a session
		
		
		//The next line enable access to the H2 console and MUST be deleted out of development environment.
        http.headers().frameOptions().disable();
	}
	
	//Static resources configurations (JavaScript, CSS, images, etc.)
	@Override
	public void configure(WebSecurity web) throws Exception {

		super.configure(web);
	}
}