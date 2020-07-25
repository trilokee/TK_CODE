package com.trilokee.flightreservation.controller;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trilokee.flightreservation.dto.ReservationUpdateRequest;
import com.trilokee.flightreservation.entities.Reservation;
import com.trilokee.flightreservation.repos.ReservationRepository;

@RestController
@CrossOrigin//with this annotation diffrent application running on different port can communicate each other
public class ReservationRestController {
	
	@Autowired
	ReservationRepository reservationRepository;
	
	
	//finding the reservation details by id and binding(serilazing  the data in json for for the client (other or 3rd party application use))
	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id)//binding the path variable to java method variable
	{
				  Reservation reservationDetails = reservationRepository.findById(id).get();//whatever data comes from the database will be serialized on the wire as to json and send back to client as postman for other application integration                     
				return reservationDetails;
		
	}
	
	
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request)//requestBody make sure that object will be constructe by "ReservationUpdateRequest request" based on the content coming in the json form by  
	{
		
		Reservation reservation = reservationRepository.findById(request.getId()).get();
		
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		
		Reservation updatedReservationDetails = reservationRepository.save(reservation);
		return updatedReservationDetails;
		
		
	}
	
	
	
	

	
	
	
	
	
	
}
