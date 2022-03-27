package com.training.pms.dao;

import org.springframework.data.repository.CrudRepository;

import com.training.pms.model.Appointment;

public interface AppointmentDAO extends CrudRepository<Appointment, Integer> {

	
}
