package com.training.pms.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.training.pms.model.Appointment;
import com.training.pms.model.Doctor;

public interface AppointmentDAO extends CrudRepository<Appointment, Integer> {

	List<Appointment> findByPatientId(int patientId);
	List<Appointment> findByDoctorId(int doctorId);
	
}
