package com.training.pms.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.pms.dao.DoctorDAO;
import com.training.pms.model.Doctor;

@Service
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	DoctorDAO doctorDAO;

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
		//Overridden (if) statement, but left logic for future implementation
		if (/*doctor.getPrice() < 0 || doctor.getQuantityOnHand() < 0*/false) {
			return "Doctor could not be saved since price or quantity on hand was less than 0";
		} else {
			doctorDAO.save(doctor);
			return "Doctor saved successfully";
		}
	}

	//------PUT------
	@Override
	public String updateDoctor(int doctorId, Doctor doctor) {
		//Overridden (if) statement, but left logic for future implementation
		if (/*doctor.getPrice() < 0 || doctor.getQuantityOnHand() < 0*/false) {
			return "Doctor (id:"+doctorId+") could not be updated since price or quantity on hand was less than 0";
		} else {
			doctorDAO.save(doctor);
			return "Doctor (id:"+doctorId+") updated successfully";
		}
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
