package com.saga.account.security;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.account.domain.Account;
import com.saga.account.dto.response.ApiErrorResponse;
import com.saga.account.enums.EStatus;
import com.saga.account.service.AccountService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
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
	private final AccountService accountService;
	private final ObjectMapper objectMapper;

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain) throws IOException, ServletException {
		
		String requestUri = request.getRequestURI();
        log.debug("Processing request for URL: {}", requestUri);
        
		try {
			Optional<CustomUserDetails> userDetailsOpt = getJwtFromRequest(request)
				    .flatMap(tokenProvider::validateTokenAndGetJws)
				    .map(jws -> buildUserDetails(jws.getPayload()));
			
			userDetailsOpt.ifPresent(userDetails -> authenticateUser(request, userDetails));
			
		    userDetailsOpt.ifPresent(this::checkAccountStatus);
			
			filterChain.doFilter(request, response);
			
	    } catch (AccessDeniedException ex) {
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

	        objectMapper.writeValue(response.getWriter(),
	                new ApiErrorResponse("Access denied"));
	        return;
	    } catch (JwtException | IllegalArgumentException ex) {
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        objectMapper.writeValue(response.getWriter(),
	                new ApiErrorResponse("Authentication required"));
	        return;
	    }
    }

	private void checkAccountStatus(CustomUserDetails userDetails) {
		Account account = accountService.getByUserId(userDetails.getId());
		
	   if (account.getStatus() != EStatus.CREATED) {
	        throw new AccessDeniedException("Account not active");
	    }
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
