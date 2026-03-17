package com.booking.auth.domain;

import java.util.UUID;

import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import com.booking.auth.enums.ERole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "auth_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
    @NotBlank
	@Size(min = 12, max = 20)
    @Column(name = "username", unique = true, nullable = false, length = 20)
	private String username;
	
	@NotBlank
	@Column(name = "password_hash", nullable = false, length = 255)
	private String passwordHash;
	
	@NotNull
	@JdbcType(PostgreSQLEnumJdbcType.class)
	@Column(name = "role", nullable = false)
	private ERole role;
}
