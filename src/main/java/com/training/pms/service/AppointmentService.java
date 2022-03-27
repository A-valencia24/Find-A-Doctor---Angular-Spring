package com.training.pms.service;

import java.util.List;

import com.training.pms.model.Appointment;

public interface AppointmentService {

	//------GET------
	public List<Appointment> getAppointments();
	public Appointment getAppointment(int appointmentId);
	public boolean isAppointmentExists(int appointmentId);

	//------POST------
	public String addAppointment(Appointment appointment);

	//------PUT------
	public String updateAppointment(int appointmentId, Appointment appointment);

	//------DELETE------
	public String deleteAppointment(int appointmentId);
	public String deleteAppointment();
	
}
