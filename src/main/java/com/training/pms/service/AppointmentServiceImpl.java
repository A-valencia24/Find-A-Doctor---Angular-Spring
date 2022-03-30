package com.training.pms.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.pms.dao.AppointmentDAO;
import com.training.pms.model.Appointment;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	AppointmentDAO appointmentDAO;

	//------GET------
	@Override
	public List<Appointment> getAppointments() {
		return (List<Appointment>) appointmentDAO.findAll();
	}

	@Override
	public Appointment getAppointment(int appointmentId) {
		Optional<Appointment> appointment = appointmentDAO.findById(appointmentId);
		return appointment.get();
	}

	@Override
	public boolean isAppointmentExists(int appointmentId) {
		return appointmentDAO.existsById(appointmentId);
	}

	//------POST------
	@Override
	public String addAppointment(Appointment appointment) {
		//Overridden (if) statement, but left logic for future implementation
		if (/*appointment.getPrice() < 0 || appointment.getQuantityOnHand() < 0*/false) {
			return "Appointment could not be saved since price or quantity on hand was less than 0";
		} else {
			appointmentDAO.save(appointment);
			return "Appointment saved successfully";
		}
	}

	@Override
	public String addAppointments(List<Appointment> appointments) {
		appointmentDAO.saveAll(appointments);
		String groupIds = "";
		for (int i = 0; i < appointments.size(); i++) {
			groupIds = groupIds + appointments.get(i).getAppointmentId() + ",";
		}
		return "Appointment group (ids:"+groupIds+") saved successfully";
	}

	//------PUT------
	@Override
	public String updateAppointment(int appointmentId, Appointment appointment) {
		//Overridden (if) statement, but left logic for future implementation
		if (/*appointment.getPrice() < 0 || appointment.getQuantityOnHand() < 0*/false) {
			return "Appointment (id:"+appointmentId+") could not be updated since price or quantity on hand was less than 0";
		} else {
			appointmentDAO.save(appointment);
			return "Appointment (id:"+appointmentId+") updated successfully";
		}
	}

	//------DELETE------
	@Override
	public String deleteAppointment(int appointmentId) {
		appointmentDAO.deleteById(appointmentId);
		return "Appointment (id:"+appointmentId+") deleted successfully";
	}

	@Override
	public String deleteAppointment() {
		appointmentDAO.deleteAll();
		return "All appointments deleted successfully";
	}

}
