package com.saga.account.domain;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import com.saga.account.enums.EStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
	@NotNull
	@Column(name = "auth_user_id", nullable = false, unique = true)
	private UUID userId;
	
	@NotBlank
	@Size(min = 2, max = 20)
    @Column(name = "first_name", nullable = false, length = 20)
	private String firstName;
    
	@NotBlank
    @Size(min = 2, max = 20)
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;
	
	@NotNull
	@Column(name = "birthdate", nullable = false)
	private LocalDate birthdate;
	
	@NotNull
    @Email
    @Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@NotNull
	@JdbcType(PostgreSQLEnumJdbcType.class)
	@Column(name = "status", nullable = false)
	private EStatus status;
}
