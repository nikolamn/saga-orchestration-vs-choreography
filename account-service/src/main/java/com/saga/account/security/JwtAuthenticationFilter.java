package com.saga.account.security;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final TokenProvider tokenProvider;

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain) throws IOException, ServletException {
		
		String requestUri = request.getRequestURI();
        log.debug("Processing request for URL: {}", requestUri);
        
		try {
            getJwtFromRequest(request)
                    .flatMap(tokenProvider::validateTokenAndGetJws)
                    .map(jws -> buildUserDetails(jws.getPayload()))
                    .ifPresent(userDetails -> authenticateUser(request, userDetails));

        } catch (Exception e) {
            log.info("AUTH EX Authentication internal error for {}: {}", requestUri, e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private void authenticateUser(HttpServletRequest request, CustomUserDetails userDetails) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);
        
        log.info("Authenticated: {} with roles: {}", userDetails.getUsername(), userDetails.getAuthorities());
    }
	
	private CustomUserDetails buildUserDetails(Claims claims) {
        UUID userId = UUID.fromString(claims.getSubject());
        String username = claims.get("username", String.class);

        return CustomUserDetails.builder()
                .id(userId)
                .username(username)
                .build();
    }

	private Optional<String> getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(TOKEN_HEADER);
		
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
			return Optional.of(bearerToken.substring(TOKEN_PREFIX.length()));
		}
		
		return Optional.empty();
	}
	
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
}
