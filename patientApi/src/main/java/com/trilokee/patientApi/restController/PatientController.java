package com.trilokee.patientApi.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trilokee.patientApi.model.Patient;
import com.trilokee.patientApi.repository.PatientJdbcRepository;

@RequestMapping("/api")
@RestController
@CrossOrigin
public class PatientController {

	
	
	
	@Autowired
	private PatientJdbcRepository patientJdbcRepository;
	
	
	
	//get List of patient details
	
	 @GetMapping
	 public List<Patient> getPatients()
		 { 
			 return patientJdbcRepository.findAllPatients(); 
		 }
	  
	 
	 //insert new patient		
	 @PostMapping
	 public int savePatient(@RequestBody Patient patient)
	 { 
		 return patientJdbcRepository.save(patient);
	 }
	
	
	//find a patient by custom id
	 @GetMapping("/{id}")
	 public Patient getPatient(@PathVariable("id") int id)
	 { 
		 return patientJdbcRepository.getPatientById(id);
	 }
	  
	
	//update a patient by custom id
	 @PutMapping
	 public int updatePatient(@RequestBody Patient patient)
	 { 
		 return patientJdbcRepository.updatePatientDetails(patient);
	 }
	 
	
	//deletepatient 
	 @DeleteMapping("/{id}")
	 public int deletePatient(@PathVariable("id") int id)
	 { 
		 return patientJdbcRepository.deletePatientById(id);
	 }
	 
}
