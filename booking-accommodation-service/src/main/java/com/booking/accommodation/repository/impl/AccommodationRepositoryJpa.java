package com.booking.accommodation.repository.impl;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.accommodation.domain.Accommodation;

@Repository
public interface AccommodationRepositoryJpa extends JpaRepository<Accommodation, UUID> {}
