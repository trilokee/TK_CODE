package com.trilokee.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trilokee.flightreservation.entities.Reservation;
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
