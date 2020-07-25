package com.trilokee.flightreservation.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.trilokee.flightreservation.dto.ReservationRequest;
import com.trilokee.flightreservation.entities.Flight;
import com.trilokee.flightreservation.entities.Passenger;
import com.trilokee.flightreservation.entities.Reservation;
import com.trilokee.flightreservation.repos.FlightRepository;
import com.trilokee.flightreservation.repos.PassengerRepository;
import com.trilokee.flightreservation.repos.ReservationRepository;
import com.trilokee.flightreservation.util.EmailUtil;
import com.trilokee.flightreservation.util.PDFGenerator;


@Service
public class ReservationServiceImpl implements ReservationService {

	@Value("${com.trilokee.flightreservation.itinerary.dirpath}")
	private String ITRINERARY_DIR;//---- = "/C:/Users/Trilokee/Documents/reservations/reservation";

	@Autowired
	FlightRepository  flightRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {
		
		//make payment--request.getCardNumber();
		
		Long flightId = request.getFlightId();
		Flight flight = flightRepository.findById(flightId).get();
		
		Passenger passenger=new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		
		
		Reservation reservation =new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
	   // reservation.setNumberOfBags(1);
		Reservation savedReservation = reservationRepository.save(reservation);
		
		 //after saving the reservation info generate the pdf 
		//try with dynamic file name according to departure and arrival location----savedReservation.
		//reservation="savedReservation.getFlight().getDepartureCity()+savedReservation.getFlight().getArrivalCity()"
				
		String filePath = ITRINERARY_DIR+savedReservation.getId()+".pdf";
		pdfGenerator.generateItinerary(savedReservation,filePath);
		
		emailUtil.sendItinerary(passenger.getEmail(), filePath);
		
		
		
		return savedReservation;
		
	}

}
