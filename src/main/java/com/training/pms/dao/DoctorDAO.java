package com.training.pms.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.training.pms.model.Doctor;
import com.training.pms.model.Patient;

public interface DoctorDAO extends CrudRepository<Doctor, Integer> {

	List<Doctor> findByEmailAndPassword(String email, String password);

	List<Doctor> findByLastnameAndStateAndSpecialty(String lastName, String state, String specialty);
	List<Doctor> findByLastnameAndState(String lastName, String state);
	List<Doctor> findByStateAndSpecialty(String state, String specialty);
	List<Doctor> findBySpecialtyAndLastname(String specialty, String lastName);
	List<Doctor> findByLastname(String lastName);
	List<Doctor> findByState(String state);
	List<Doctor> findBySpecialty(String specialty);
}
