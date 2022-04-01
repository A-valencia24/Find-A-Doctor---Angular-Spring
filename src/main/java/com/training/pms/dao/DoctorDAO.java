package com.training.pms.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.training.pms.model.Doctor;
import com.training.pms.model.Patient;

public interface DoctorDAO extends CrudRepository<Doctor, Integer> {

	List<Doctor> findByEmailAndPassword(String email, String password);

	List<Doctor> findByLastnameAndStateAndPractice(String lastName, String state, String practice);
	List<Doctor> findByLastnameAndState(String lastName, String state);
	List<Doctor> findByStateAndPractice(String state, String practice);
	List<Doctor> findByPracticeAndLastname(String practice, String lastName);
	List<Doctor> findByLastname(String lastName);
	List<Doctor> findByState(String state);
	List<Doctor> findByPractice(String practice);
}
