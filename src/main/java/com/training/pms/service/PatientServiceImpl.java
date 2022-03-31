package com.training.pms.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.training.pms.dao.PatientDAO;
import com.training.pms.model.Patient;

@Service
public class PatientServiceImpl implements PatientService {
	@Autowired
	PatientDAO patientDAO;
	
	//------LOGIN------
	@Override
	public List<Patient> login(String email, String password) {
		return (List<Patient>) patientDAO.findByEmailAndPassword(email, password);
	}

	//------GET------
	@Override
	public List<Patient> getPatients() {
		return (List<Patient>) patientDAO.findAll();
	}

	@Override
	public Patient getPatient(int patientId) {
		Optional<Patient> patient = patientDAO.findById(patientId);
		return patient.get();
	}

	@Override
	public boolean isPatientExists(int patientId) {
		return patientDAO.existsById(patientId);
	}

	//------POST------
	@Override
	public String addPatient(Patient patient) {
		patientDAO.save(patient);
		return "Patient (id:"+patient.getPatient_id()+") saved successfully";
	}

	@Override
	public String addPatients(List<Patient> patients) {
		patientDAO.saveAll(patients);
		String groupIds = "";
		for (int i = 0; i < patients.size(); i++) {
			groupIds = groupIds + patients.get(i).getPatient_id() + ",";
		}
		return "Patient group (ids:"+groupIds+") saved successfully";
	}


	//------PUT------
	@Override
	public String updatePatient(int patientId, Patient patient) {
		patientDAO.save(patient);
		return "Patient (id:"+patientId+") updated successfully";
	}

	//------DELETE------
	@Override
	public String deletePatient(int patientId) {
		patientDAO.deleteById(patientId);
		return "Patient (id:"+patientId+") deleted successfully";
	}

	@Override
	public String deletePatient() {
		patientDAO.deleteAll();
		return "All patients deleted successfully";
	}

}
