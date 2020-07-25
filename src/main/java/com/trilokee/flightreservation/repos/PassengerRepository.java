package com.trilokee.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trilokee.flightreservation.entities.Passenger;


public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
