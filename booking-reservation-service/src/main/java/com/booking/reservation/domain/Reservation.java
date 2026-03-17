package com.booking.reservation.domain;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import com.booking.reservation.enums.EStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
@Entity
@Table(name = "reservation")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
	@NotNull
	@Column(name = "accommodation_id", updatable = false, nullable = false)
	private UUID accommodationId;
	
	@NotNull
	@Column(name = "user_id", updatable = false, nullable = false)
	private UUID userId;
	
	@NotNull
	@Column(name = "beginning", nullable = false)
	private LocalDate beginning;
	
	@NotNull
	@Column(name = "ending", nullable = false)
	private LocalDate ending;
	
	@NotNull
	@Positive
	@Column(name = "guests", nullable = false)
	private Integer guests;
	
	@NotNull
	@JdbcType(PostgreSQLEnumJdbcType.class)
	@Column(name = "status", nullable = false)
	private EStatus status;
}
