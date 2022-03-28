package com.training.pms.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.training.pms.model.Doctor;
import com.training.pms.model.Patient;

public interface DoctorDAO extends CrudRepository<Doctor, Integer> {

	List<Doctor> findByEmailAndPassword(String email, String password);
	
}
