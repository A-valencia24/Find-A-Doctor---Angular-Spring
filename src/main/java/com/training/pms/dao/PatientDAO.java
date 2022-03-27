package com.training.pms.dao;

import org.springframework.data.repository.CrudRepository;

import com.training.pms.model.Patient;

public interface PatientDAO extends CrudRepository<Patient, Integer> {

	
}
