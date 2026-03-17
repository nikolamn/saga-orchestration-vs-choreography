package com.booking.accommodation.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import com.booking.accommodation.enums.AmenityType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;


@Entity
@Builder
@Table(name = "accommodation")
public class Accommodation {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	@NotBlank
	@Size(min = 5, max = 30)
	@Column(name = "name", nullable = false)
	private String name;

	@NotBlank
	@Size(min = 5, max = 100)
	@Column(name = "description", nullable = false)
	private String description;

	@Valid
	@Embedded
	private Address address;
	
	@Builder.Default
	@ElementCollection(targetClass = AmenityType.class)
	@CollectionTable(
		name = "accommodation_amenity",
		joinColumns = @JoinColumn(name = "accommodation_id")
	)
	@Column(name = "type")
	@JdbcType(PostgreSQLEnumJdbcType.class)
	private Set<AmenityType> amenities = new HashSet<>();
	
	@Column(name = "owner_id", nullable = false)
	private UUID ownerId;

	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "accommodation_id")
	private List<Availability> availability = new ArrayList<>();
	
	@Positive
	@Column(name = "min_guests", nullable = false)
	private int minNumberOfGuests;   
	
	@Positive
	@Column(name = "max_guests", nullable = false)
	private int  maxNumberOfGuests;
	
//	photos
}
