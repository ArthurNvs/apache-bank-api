package br.com.theapache.apachebank.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.theapache.apachebank.models.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	// The next two attributes are injected from application.properties, that's why
	// you shouldn't use @Autowired.
	@Value("${forum.jwt.expiration}")
	private String expiration;

	@Value("${forum.jwt.secret}")
	private String secret;

	public String generateToken(Authentication authentication) {
		Customer logged = (Customer) authentication.getPrincipal();
		Date today = new Date();
		Date expirationTime = new Date(today.getTime() + Long.parseLong(expiration));

		return Jwts.builder()
				.setIssuer("Apache Bank API")
				.setSubject(logged.getId().toString()).setIssuedAt(today) //inserts the Customer id into "subject"
				.setExpiration(expirationTime)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public Long getCustomerId(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
}