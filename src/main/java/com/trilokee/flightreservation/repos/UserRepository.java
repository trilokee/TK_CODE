package com.trilokee.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trilokee.flightreservation.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	//@Query("from USER where email=:email")//spring data(jpa) through hibernet will execute this query when the method "finfFlights()" will be invoked and will create a native qsl internally and will get the results from the database in the form of list
	User findByEmail(String email);

}
