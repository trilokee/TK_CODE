package com.trilokee.patientApi.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.trilokee.patientApi.model.Patient;

@Repository
public class PatientJdbcRepository implements RowMapper {
	
	
	@Override	
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Patient patient = new Patient();
		patient.setId(rs.getInt("id"));
		patient.setFirstName(rs.getString("First_Name"));
		patient.setLastName(rs.getString("last_name"));
		patient.setAge(rs.getInt("age"));
		return patient;
		
	}
	
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	 //get all patients
	public List<Patient> findAllPatients(){
		
		return jdbcTemplate.query("select * from patient", new BeanPropertyRowMapper<Patient>(Patient.class));
		
	}
	
	 //insert new patient	
	public int save(Patient patient)
	{
		return jdbcTemplate.update("insert into patient(id,first_name,last_name,age)"+"values(?,?,?,?)", 
				new Object[] {patient.getId(),patient.getFirstName(),patient.getLastName(),patient.getAge()});
	}

	
	 //get a patient by custom id
	
	public Patient getPatientById(int id) {
		return jdbcTemplate.queryForObject("select * from patient where id=?", new Object[] { id },new BeanPropertyRowMapper<Patient>(Patient.class)) ;
		
	}
	
	
	//update a patient by custom id
	public int updatePatientDetails(Patient patient)
	{
		return jdbcTemplate.update("update patient " + " set first_name = ?, last_name = ?, age = ? " + " where id = ?",
				new Object[] { patient.getFirstName(), patient.getLastName(), patient.getAge(),
						patient.getId() });
	}
	
	//delete a patient
	public int deletePatientById(int id) {
		return jdbcTemplate.update("delete from patient where id=?", new Object[] { id }) ;
		
	}

	
}
