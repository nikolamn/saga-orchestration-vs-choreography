package com.booking.account.domain;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import com.booking.account.enums.EGender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
	private UUID authUserId;
	
    @NotBlank
	@Size(min = 2, max = 20, message = "First name must containe between 2 and 20 characters")
    @Column(name = "first_name", nullable = false, length = 20)
	private String firstName;
    
    @NotBlank
    @Size(min = 2, max = 20, message = "Last name must containe between 2 and 20 characters")
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;
    
	@Enumerated(EnumType.STRING)
	@JdbcType(PostgreSQLEnumJdbcType.class)
	@Column(name = "gender", nullable = false)
    private EGender gender;
	
	@NotNull
	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;
	
    @NotBlank
    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false, unique = true)
	private String email;
}
