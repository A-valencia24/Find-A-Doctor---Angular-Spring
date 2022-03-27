package com.training.pms.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.pms.dao.PatientDAO;
import com.training.pms.model.Patient;

@Service
public class PatientServiceImpl implements PatientService {
	@Autowired
	PatientDAO patientDAO;

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
		//Overridden (if) statement, but left logic for future implementation
		if (/*patient.getPrice() < 0 || patient.getQuantityOnHand() < 0*/false) {
			return "Patient could not be saved since price or quantity on hand was less than 0";
		} else {
			patientDAO.save(patient);
			return "Patient saved successfully";
		}
	}

	//------PUT------
	@Override
	public String updatePatient(int patientId, Patient patient) {
		//Overridden (if) statement, but left logic for future implementation
		if (/*patient.getPrice() < 0 || patient.getQuantityOnHand() < 0*/false) {
			return "Patient (id:"+patientId+") could not be updated since price or quantity on hand was less than 0";
		} else {
			patientDAO.save(patient);
			return "Patient (id:"+patientId+") updated successfully";
		}
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
