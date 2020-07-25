package com.trilokee.flightreservation.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role extends AbstractEntity implements GrantedAuthority {
	
	
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

	private static final long serialVersionUID = -8803713490248870040L;
	private String name;
	
	/*
	 * public Role(String name, Set<User> users) { super(); this.name = name;
	 * this.users = users; }
	 */
	
	 public Role() {
	  
	 }
	 
	@ManyToMany(mappedBy="roles")//this roles is from set<Role> User.java file
	private Set<User> users;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String getAuthority() {
		//here name is the Role name
		return name;
	}

	

}
