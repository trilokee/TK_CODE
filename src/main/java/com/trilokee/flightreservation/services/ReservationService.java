package com.trilokee.flightreservation.services;

import com.trilokee.flightreservation.dto.ReservationRequest;
import com.trilokee.flightreservation.entities.Reservation;

public interface ReservationService {
	
	public Reservation bookFlight(ReservationRequest request);

}
