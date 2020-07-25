package com.trilokee.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trilokee.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {


}
