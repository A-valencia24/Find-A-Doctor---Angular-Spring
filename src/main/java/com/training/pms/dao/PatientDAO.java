package com.training.pms.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.training.pms.model.Patient;

public interface PatientDAO extends CrudRepository<Patient, Integer> {

	List<Patient> findByEmailAndPassword(String email, String password);
	
}
