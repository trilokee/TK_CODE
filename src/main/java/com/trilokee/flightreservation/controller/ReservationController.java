package com.trilokee.flightreservation.controller;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;

import com.trilokee.flightreservation.dto.ReservationRequest;
import com.trilokee.flightreservation.entities.Flight;
import com.trilokee.flightreservation.entities.Reservation;
//import com.trilokee.flightreservation.entities.Flight;
import com.trilokee.flightreservation.repos.FlightRepository;
import com.trilokee.flightreservation.services.ReservationService;

@Controller 
public class ReservationController {
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	ReservationService reservationService;
	
	
	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId,ModelMap modelMap)//by using [@RequestParam("flightId") Long flightId] we are getting the flightId from the "displayFlights.jsp"  and by using [ModelMap modelMap and modelMap.addAttribute("flightIdKey", flight);] flightIdKey - we are sending the corresponding flightId information to "completeReservation.jsp" page                                                 
	{
	Flight flight=	flightRepository.findOne(flightId);
		modelMap.addAttribute("flightIdKey",flight);
		
		return "completeReservation";
		
	}
	
	
	@RequestMapping(value="/completeReservation",method=RequestMethod.POST)
	public String completeReservation(ReservationRequest request,ModelMap modelMap )                                                 
	{
	
		Reservation reservation = reservationService.bookFlight(request);
		
		modelMap.addAttribute("confMsgKey","Reservation created successfully with the id "+reservation.getId());
		return "reservationConfirmation";
		
	}
	
	
	
	
	

}
