package com.trilokee.flightreservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service 
public class SecurityServiceImpl implements SecurityService {

	@Autowired 
	UserDetailsService userDetailsService;//always inject the Interface spring will always fetch the implementation for us 
	
	@Autowired 
	AuthenticationManager authenticationManager;
	
	
	
	@Override
	public boolean login(String username, String password) {
		System.out.println("inside login");
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		//System.out.println("got the token");
		UsernamePasswordAuthenticationToken token =new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
	//	System.out.println(userDetails.getUsername()+"   "+userDetails.getAuthorities()+"  "+userDetails.getPassword());
		
		try {
		authenticationManager.authenticate(token);//a state will be added to the token
		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

			}
		//System.out.println("authenticate the token");
		boolean result = token.isAuthenticated();
		//after this we will save the user state or session so that once the user login will be till logout in the spring  security context

		if(result)//if resut ==true
		{
			SecurityContextHolder.getContext().setAuthentication(token);//SecurityContextHolder has a static method getContext();--storing the state of the token(ser session we can call) in spring security context---every time a request comes the spring security will check whether user is in the same security session or not till logout
			//after this spring womt ask againg and again for login,becoz its saving the state of the user till logut
		}
		
		return result;
	}

}