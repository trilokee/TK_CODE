package com.trilokee.patientApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trilokee.patientApi.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

	
}
