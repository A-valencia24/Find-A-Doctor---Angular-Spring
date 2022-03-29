package com.training.pms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.training.pms.model.Patient;

public interface PatientDAO extends CrudRepository<Patient, Integer> {

	List<Patient> findByEmailAndPassword(String email, String password);
	
	// this is an attempt to pull the id for testing, but I'm not sure how to call it from the testing class.
	@Query("SELECT MAX(patient_id) FROM tables")
	public int maxId();
	
}
