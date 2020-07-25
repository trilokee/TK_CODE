package com.trilokee.patientApi.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trilokee.patientApi.model.Patient;
import com.trilokee.patientApi.repository.PatientJdbcRepository;
import com.trilokee.patientApi.repository.PatientRepository;


@RequestMapping("/api")
@RestController
@CrossOrigin
public class PatientApiController {
	
	//////////////for jpa 
	/*
	 * private PatientRepository patientRepository;
	 * 
	 * @Autowired PatientApiController(PatientRepository patientRepository) {
	 * this.patientRepository = patientRepository; }
	 * 
	 * @RequestMapping(value = "/patients", method = RequestMethod.POST) public
	 * Patient savePatient(@RequestBody Patient patient) { return
	 * patientRepository.save(patient); }
	 * 
	 * @RequestMapping(value = "/patients/{id}", method = RequestMethod.GET) public
	 * Patient getPatient(@PathVariable("id") int id) { return
	 * patientRepository.findById(id).get(); }
	 * 
	 * @RequestMapping(value = "/getAllpatients", method = RequestMethod.GET) public
	 * List<Patient> getPatients() { return patientRepository.findAll(); }
	 * 
	 * @RequestMapping(value = "/deletePatient/{id}", method = RequestMethod.GET)
	 * public void deletePatient(@PathVariable("id") int id) {
	 * patientRepository.deleteById(id); }
	 * 
	 * @RequestMapping(value = "/updatePatient/{id}", method = RequestMethod.PUT)
	 * public Patient updatePatient(@PathVariable int id, Patient patientRequest) {
	 * 
	 * Patient patient = patientRepository.findById(patientRequest.getId()).get();
	 * patient.setId(patient.getId()); patient.setFirstName(patient.getFirstName());
	 * patient.setLastName(patient.getLastName()); patient.setAge(patient.getAge());
	 * return patient;
	 * 
	 * }
	 */
	
//for jdbc
	@Autowired
	private PatientJdbcRepository patientJdbcRepository;
	
	
	
	
	//get all the patient
	 @RequestMapping(value = "/getAllpatients", method = RequestMethod.GET) 
	 public List<Patient> getPatients()
		 { 
			 return patientJdbcRepository.findAllPatients(); 
		 }
	  
	 
	 //insert new patient		
	 @RequestMapping(value = "/patients", method = RequestMethod.POST)
	 public int savePatient(@RequestBody Patient patient)
	 { 
		 return patientJdbcRepository.save(patient);
	 }
	
	
	//find a patient by custom id
	 @RequestMapping(value = "/patients/{id}", method = RequestMethod.GET)
	 public Patient getPatient(@PathVariable("id") int id)
	 { 
		 return patientJdbcRepository.getPatientById(id);
	 }
	  
	
	//update a patient by custom id
	 @RequestMapping(value = "/updatePatients", method = RequestMethod.POST)
	 public int updatePatient(@RequestBody Patient patient)
	 { 
		 return patientJdbcRepository.updatePatientDetails(patient);
	 }
	 
	
	//deletepatient 
	 @RequestMapping(value = "/deletePatients/{id}", method = RequestMethod.GET)
	 public int deletePatient(@PathVariable("id") int id)
	 { 
		 return patientJdbcRepository.deletePatientById(id);
	 }
	 
	
	
	
	
	
	
	
	
	
}
