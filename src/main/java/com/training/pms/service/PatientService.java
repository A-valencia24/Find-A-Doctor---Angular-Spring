package com.training.pms.service;

import java.util.List;

import com.training.pms.model.Patient;

public interface PatientService {

	//------LOGIN------
	public List<Patient> login(String email, String password);

	//------GET------
	public List<Patient> getPatients();
	public Patient getPatient(int patientId);
	public boolean isPatientExists(int patientId);

	//------POST------
	public String addPatient(Patient patient);
	public String addPatients(List<Patient> patients);

	//------PUT------
	public String updatePatient(int patientId, Patient patient);

	//------DELETE------
	public String deletePatient(int patientId);
	public String deletePatient();
	
}
