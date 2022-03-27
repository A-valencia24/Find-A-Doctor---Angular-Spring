package com.training.pms.service;

import java.util.List;

import com.training.pms.model.Patient;

public interface PatientService {

	//------GET------
	public List<Patient> getPatients();
	public Patient getPatient(int patientId);
	public boolean isPatientExists(int patientId);

	//------POST------
	public String addPatient(Patient patient);

	//------PUT------
	public String updatePatient(int patientId, Patient patient);

	//------DELETE------
	public String deletePatient(int patientId);
	public String deletePatient();
	

//	public List<Patient> getPatientByName(String patientName);
//	public List<Patient> getPatientsByPriceRange(int lowerPrice, int upperPrice);
//	public int getPatientQuanitiyOnHand(int patientId);
}
