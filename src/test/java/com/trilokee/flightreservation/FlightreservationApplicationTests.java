package com.trilokee.flightreservation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import com.trilokee.flightreservation.entities.User;
import com.trilokee.flightreservation.repos.UserRepository;

@SpringBootTest
public class FlightreservationApplicationTests {

	/*
	 * @Autowired UserRepository userRepository;
	 * 
	 * @Autowired UserDetails userDetails;
	 * 
	 * 
	 * @Test public void finddetails() {
	 * 
	 * String email="ruchi@gmail.com"; User user =
	 * userRepository.findByEmail(email); System.out.println(user);
	 * System.out.println(user.getEmail()); System.out.println(user.getFirstName());
	 * System.out.println(user.getLastName());
	 * System.out.println(user.getPassword()); System.out.println(user.getId());
	 * System.out.println(user.getRoles().toString());
	 * System.out.println(userDetails.getAuthorities());
	 * 
	 * System.out.println(userDetails.getPassword());
	 * System.out.println(userDetails.getUsername()); }
	 */
}
