package com.training.pms.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.training.pms.dao.DoctorDAO;
import com.training.pms.model.Doctor;
import com.training.pms.model.Patient;

@Service
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	DoctorDAO doctorDAO;
	
	//------LOGIN------
	@Override
	public List<Doctor> login(String email, String password) {
		return (List<Doctor>) doctorDAO.findByEmailAndPassword(email, password);
	}
	
	//------SEARCH------
	@Override
	public List<Doctor> search(String lastName, String state, String specialty) {
		boolean lastNamePresent = (!lastName.equals("null"));
		boolean statePresent = (!state.equals("null"));
		boolean specialtyPresent = (!specialty.equals("null"));
		
		if (lastNamePresent && statePresent && specialtyPresent) {
			return (List<Doctor>) doctorDAO.findByLastnameAndStateAndSpecialty(lastName, state, specialty);
		} else if (lastNamePresent && statePresent) {
			return (List<Doctor>) doctorDAO.findByLastnameAndState(lastName, state);
		} else if (statePresent && specialtyPresent) {
			return (List<Doctor>) doctorDAO.findByStateAndSpecialty(state, specialty);
		} else if (specialtyPresent && lastNamePresent) {
			return (List<Doctor>) doctorDAO.findBySpecialtyAndLastname(specialty, lastName);
		} else if (lastNamePresent) {
			return (List<Doctor>) doctorDAO.findByLastname(lastName);
		} else if (statePresent) {
			return (List<Doctor>) doctorDAO.findByState(state);
		} else if (specialtyPresent) {
			return (List<Doctor>) doctorDAO.findBySpecialty(specialty);
		} else {
			return (List<Doctor>) doctorDAO.findByLastname("");
		}
	}

	//------GET------
	@Override
	public List<Doctor> getDoctors() {
		return (List<Doctor>) doctorDAO.findAll();
	}

	@Override
	public Doctor getDoctor(int doctorId) {
		Optional<Doctor> doctor = doctorDAO.findById(doctorId);
		return doctor.get();
	}

	@Override
	public boolean isDoctorExists(int doctorId) {
		return doctorDAO.existsById(doctorId);
	}

	//------POST------
	@Override
	public String addDoctor(Doctor doctor) {
		doctorDAO.save(doctor);
		return "Doctor saved successfully";
	}
	@Override
	public String addDoctors(List<Doctor> doctors) {
		doctorDAO.saveAll(doctors);
		String groupIds = "";
		for (int i = 0; i < doctors.size(); i++) {
			groupIds = groupIds + doctors.get(i).getDoctor_id() + ",";
		}
		return "Doctor group (ids:"+groupIds+") saved successfully";
	}

	//------PUT------
	@Override
	public String updateDoctor(int doctorId, Doctor doctor) {
		doctorDAO.save(doctor);
		return "Doctor (id:"+doctorId+") updated successfully";
	}

	//------DELETE------
	@Override
	public String deleteDoctor(int doctorId) {
		doctorDAO.deleteById(doctorId);
		return "Doctor (id:"+doctorId+") deleted successfully";
	}

	@Override
	public String deleteDoctor() {
		doctorDAO.deleteAll();
		return "All doctors deleted successfully";
	}


}
