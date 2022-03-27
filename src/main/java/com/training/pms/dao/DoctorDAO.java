package com.training.pms.dao;

import org.springframework.data.repository.CrudRepository;

import com.training.pms.model.Doctor;

public interface DoctorDAO extends CrudRepository<Doctor, Integer> {

	
}
