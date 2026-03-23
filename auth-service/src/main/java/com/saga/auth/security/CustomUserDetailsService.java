package com.saga.auth.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.saga.auth.domain.User;
import com.saga.auth.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User authUser = service.getByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("USERNAME: %s not found", username)));
        
        return toUserDetails(authUser);
    }

    private CustomUserDetails toUserDetails(User user) {
        return CustomUserDetails.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPasswordHash())
                .build();
    }
}