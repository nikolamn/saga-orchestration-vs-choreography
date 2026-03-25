package com.saga.account.security;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class TokenProvider {

	public static final String TOKEN_TYPE = "JWT";

	@Value("${app.jwt.secret}")
	private String jwtSecret;

	@Value("${app.jwt.expiration.minutes}")
	private Long jwtExpirationMinutes;

	private SecretKey key;
	
	@PostConstruct
	public void init() {
		byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String generate(Authentication authentication) {
		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

		Instant now = Instant.now();

		return Jwts.builder()
				.header()
				.add("typ", TOKEN_TYPE).and()
				.signWith(this.key, Jwts.SIG.HS512)
				.issuedAt(Date.from(now))
				.expiration(Date.from(now.plus(jwtExpirationMinutes, ChronoUnit.MINUTES)))
				.id(UUID.randomUUID().toString())
				.subject(user.getId().toString())
				.claim("username", user.getUsername())
				.compact();
	}

	public Optional<Jws<Claims>> validateTokenAndGetJws(String token) {
		try {

			return Optional.of(Jwts.parser()
					.verifyWith(this.key)
					.build()
					.parseSignedClaims(token));
		} catch (Exception e) {
			return Optional.empty();
		}
	}
	
	public String extractSubject(String token) {
		return validateTokenAndGetJws(token)
				.map(jws -> jws.getPayload().getSubject())
				.orElse(null);
	}
}