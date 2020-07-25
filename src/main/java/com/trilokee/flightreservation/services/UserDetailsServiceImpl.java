package com.trilokee.flightreservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.trilokee.flightreservation.entities.User;
import com.trilokee.flightreservation.repos.UserRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository  userRepository;  
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//System.out.println("inside load user"+username);
		System.out.println();
		User user = userRepository.findByEmail(username);
		//System.out.println(user.);
		//UserDetailsis an interface which and User(user.getEmail(),user.getPassword(),user.getRoles()) is the implementation of the UserDetails interface 
		//System.out.println("inside userserviceimpl service"+user.getEmail());
		//System.out.println(user.getPassword());
		
		//System.out.println(user.getRoles());
	//return a user object from the spring security by giving our own user details-------here Role must implements gramted authority from spring
		
		  if(user==null)
		  {
			  throw new UsernameNotFoundException("user not found for email"+username);
		  }
		 
		 		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),user.getRoles());
	}

}
