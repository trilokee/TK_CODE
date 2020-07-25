	package com.trilokee.flightreservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.trilokee.flightreservation.entities.User;
import com.trilokee.flightreservation.repos.UserRepository;
import com.trilokee.flightreservation.services.SecurityService;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Autowired
	private SecurityService securityService;
	
	
	@RequestMapping("/showReg")
	public String showRegistrationPage()
	{
		
		return "login/registerUser";
	}

	
	
	@RequestMapping(value="/registerUser",method=RequestMethod.POST)
	public String register(@ModelAttribute("user") User user)
	{
		System.out.println("inside user register controllers");
		
	user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		
		return "login/login";
	}

	@RequestMapping("/showLogin")
	public String showLoginPage()
	{
		System.out.println("inside login page");
		return "login/login";
	}
	
	@RequestMapping("/showLogout")
	public String showLogoutPage()
	{
		
		return "logout";
	}
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String userLogin(@RequestParam("email") String email ,@RequestParam("password") String password,ModelMap modelMap)//this email and password are from login.jsp page
	{
		
		System.out.println("before validation user");	
		System.out.println(email);
		System.out.println(password);
		//we will use the spring security service
	//boolean loginResponse = securityService.login(email,password);
		boolean loginResponse = securityService.login(email,password);
		//below can be used without spring security
		//User user = userRepository.findByEmail(email);
	System.out.println(email);
	System.out.println(password);
		//if(user.getPassword().equals(password))
	//System.out.println("after validation user"+loginResponse);
		if(loginResponse)
		{
			return "findFlights";
		}
		else
		{
			modelMap.addAttribute("msg", "Invalid username or Password. please try again");
			
		}
		return "login/login";
	}
	
}
