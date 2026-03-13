package com.booking.auth.infrastructure.security;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.booking.auth.exception.JwtAuthenticationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TokenProvider {

	public static final String TOKEN_TYPE = "JWT";

	@Value("${app.jwt.secret}")
	private String jwtSecret;

	@Value("${app.jwt.expiration.minutes}")
	private Long jwtExpirationMinutes;

	public String generate(Authentication authentication) {
		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

		List<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		byte[] signingKey = jwtSecret.getBytes();

		Instant now = Instant.now();

		return Jwts.builder().header().add("typ", TOKEN_TYPE).and()
				.signWith(Keys.hmacShaKeyFor(signingKey), Jwts.SIG.HS512).issuedAt(Date.from(now))
				.expiration(Date.from(now.plusSeconds(60 * jwtExpirationMinutes))).id(UUID.randomUUID().toString())
				.subject(user.getId().toString()).claim("username", user.getUsername()).claim("role", roles).compact();
	}

	public Optional<Jws<Claims>> validateTokenAndGetJws(String token) {
		try {
			byte[] signingKey = jwtSecret.getBytes();

			Jws<Claims> jws = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(signingKey)).build().parseSignedClaims(token);

			return Optional.of(jws);
		} catch (JwtAuthenticationException | IllegalArgumentException e) {
	        log.error("JWT parsing failed: {}", e.getMessage());
			throw new JwtAuthenticationException("JWT ERROR");
		}
	}
}