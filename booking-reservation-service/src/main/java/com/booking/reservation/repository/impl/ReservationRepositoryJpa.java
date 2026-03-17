package com.booking.reservation.repository.impl;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.reservation.domain.Reservation;

@Repository
public interface ReservationRepositoryJpa extends JpaRepository<Reservation, UUID> {}
