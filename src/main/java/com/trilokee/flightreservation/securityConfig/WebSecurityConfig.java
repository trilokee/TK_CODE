package com.trilokee.flightreservation.securityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()//the bean of this wonnt create automatically manually we have to provide at run time
	{
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// antMatchers("/admin/showAddFlight").hasAnyAuthority("ADMIN")---------only
		// admin has the authority to access the "/admin/showAddFlight" url
		// csrf---cross side scripting report
//-----"/reservations/*" is the REST uris so we wont restrict these urls becoz both applications(flightcheckin and flightreservation) are our internal 
		
		try {
		http.authorizeRequests()
				.antMatchers("/showReg", "/", "/index.html", "/registerUser","/login", "/showLogin", "/login/*","/reservations/*","/showlogout")
				.permitAll()
				.antMatchers("/admin/showAddFlight").hasAnyAuthority("ADMIN").anyRequest().authenticated()
				.and().logout().permitAll()
				.and().csrf().disable();
				
	} catch (Exception e) {

		// TODO Auto-generated catch block

		e.printStackTrace();

		}

	}// ---"/" is the root context
	
	
	
	//manually we have to expose the authentication manager bean
	//check below code may be disabled
	
	  @Bean(name=BeanIds.AUTHENTICATION_MANAGER)
	  @Override
	  public AuthenticationManager authenticationManagerBean() throws Exception
	  {
	    return super.authenticationManagerBean();
	  }
	 

}
