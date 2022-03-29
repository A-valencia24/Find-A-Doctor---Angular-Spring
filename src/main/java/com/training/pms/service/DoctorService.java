package com.training.pms.service;

import java.util.List;

import com.training.pms.model.Doctor;
import com.training.pms.model.Patient;

public interface DoctorService {

	//------LOGIN------
	public List<Doctor> login(String email, String password);

	//------SEARCH------
	public List<Doctor> search(String lastName, String state, String specialty);

	//------GET------
	public List<Doctor> getDoctors();
	public Doctor getDoctor(int doctorId);
	public boolean isDoctorExists(int doctorId);

	//------POST------
	public String addDoctor(Doctor doctor);

	//------PUT------
	public String updateDoctor(int doctorId, Doctor doctor);

	//------DELETE------
	public String deleteDoctor(int doctorId);
	public String deleteDoctor();
	
}
