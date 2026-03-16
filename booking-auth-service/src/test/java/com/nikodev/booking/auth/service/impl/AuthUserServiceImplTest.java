package com.nikodev.booking.auth.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

	


@ExtendWith(MockitoExtension.class)
public class AuthUserServiceImplTest {

//    @Mock
//    private AuthUserRepository repository;
//    
//    @Mock
//    private AuthUserMapper mapper;
//    
//    @Mock
//    private PasswordEncoder encoder;
//    
//    @InjectMocks
//    private AuthUserServiceImpl authUserService; 

    
//    @Test
//    void shouldThrowWhenUsernameExist() {
//    	AuthRegisterRequest dto = new AuthRegisterRequest("john", "pass", "HOST");
//    	
//    	when(repository.existsByUsername("john")).thenReturn(true);
//    	
//    	assertThrows(DuplicateUserInfoException.class, () -> authUserService.saveAuthUser(dto));
//    	
//    	verify(repository, never()).save(any());
//    }
//    
//    @Test
//    void shouldSaveAuthUserWhenUseranmeNotExists() {
//    	AuthRegisterRequest dto = new AuthRegisterRequest("john", "pass", "HOST");
//    	
//    	when(repository.existsByUsername("john")).thenReturn(false);
//    	
//    	AuthUser user = new AuthUser();
//    	when(mapper.toDomain(dto)).thenReturn(user);
//    	
//    	when(encoder.encode("pass")).thenReturn("encodedPass");
//    	
//    	authUserService.saveAuthUser(dto);
//    	
//    	verify(repository).save(user);
//    	assertEquals("encodedPass", user.getPasswordHash());
//    }
//    
//    @Test
//    void shouldReturnUserByUsername() {
//    	AuthUser user = new AuthUser();
//    	when(repository.findByUsername("john")).thenReturn(Optional.of(user));
//    	
//    	Optional<AuthUser> result = authUserService.getAuthUserByUsername("john");
//    	
//    	assertTrue(result.isPresent());
//    }
//    
//    @Test
//    void shouldReturnEmptyIfNotExists() {
//    	when(repository.findByUsername("john")).thenReturn(Optional.empty());
//    	
//    	Optional<AuthUser> result = authUserService.getAuthUserByUsername("john");
//    	
//    	assertThat(result).isEmpty();
//    }
}
