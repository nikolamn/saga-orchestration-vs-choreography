package com.booking.auth.infrastructure.security;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.booking.auth.api.rest.mapper.AuthUserMapper;
import com.booking.auth.core.domain.AuthUser;
import com.booking.auth.core.service.AuthUserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUserService service;
    private final AuthUserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        AuthUser authUser = service.getAuthUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("USERNAME: %s NOT FOUND", username)));
        
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(authUser.getRole().toString()));
        
        return mapper.toCustomAuthUserDetails(authUser, authorities);
    }
}