package com.training.pms.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.pms.dao.DoctorDAO;
import com.training.pms.model.Doctor;
import com.training.pms.model.Doctor;
import com.training.pms.service.DoctorService;
import com.training.pms.service.DoctorServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("doctor")
public class DoctorController {

	private static Logger logger = org.apache.log4j.Logger.getLogger(DoctorController.class);
	
	@Autowired
	DoctorService doctorService = new DoctorServiceImpl();

	//------LOGIN------
	@GetMapping("login/{email}/{password}")
	public ResponseEntity<List<Doctor>> login(@PathVariable("email") String email, @PathVariable("password") String password) {
		List<Doctor> result = doctorService.login(email, password);
		ResponseEntity<List<Doctor>> responseEntity = null;
		if (result.size() == 0) {
			responseEntity = new ResponseEntity<List<Doctor>>(result,HttpStatus.NOT_FOUND);
			logger.info(email+" failed to log in");
		} else {
			responseEntity = new ResponseEntity<List<Doctor>>(result,HttpStatus.OK);
			logger.info(email+" logged in");
		}
		return responseEntity;
	}

	//------SEARCH------
	@GetMapping("search/{lastName}/{state}/{specialty}")
	public ResponseEntity<List<Doctor>> search(@PathVariable("lastName") String lastName, @PathVariable("state") String state, @PathVariable("specialty") String specialty) {
		List<Doctor> result = doctorService.search(lastName, state, specialty);
		for (Doctor doctor : result) {
			doctor.setPassword(null);
		}
		ResponseEntity<List<Doctor>> responseEntity = null;
		if (result.size() == 0) {
			responseEntity = new ResponseEntity<List<Doctor>>(result,HttpStatus.NOT_FOUND);
		} else {
			responseEntity = new ResponseEntity<List<Doctor>>(result,HttpStatus.OK);
		}
		logger.info("Searched for doctors with parameters:"+lastName+"/"+state+"/"+specialty+", found ("+result.size()+")");
		return responseEntity;
	}
	
	//------GET------
	@GetMapping
	public ResponseEntity<List<Doctor>> getDoctors() {
		List<Doctor> result = doctorService.getDoctors();
		ResponseEntity<List<Doctor>> responseEntity = null;
		if (result.size() == 0) {
			responseEntity = new ResponseEntity<List<Doctor>>(result,HttpStatus.NO_CONTENT);
		} else {
			responseEntity = new ResponseEntity<List<Doctor>>(result,HttpStatus.OK);
		}
		logger.info("Doctor table pulled");
		return responseEntity;
	}
	
	@GetMapping("{doctorId}")
	public ResponseEntity<Doctor> getByDoctorId(@PathVariable("doctorId")int doctorId) {
		ResponseEntity<Doctor> responseEntity = null;
		Doctor doctor = new Doctor();
		if (doctorService.isDoctorExists(doctorId)) {
			doctor = doctorService.getDoctor(doctorId);
			responseEntity = new ResponseEntity<Doctor>(doctor,HttpStatus.OK);
			logger.info("Pulled doctor with id: "+doctorId);
		} else {
			responseEntity = new ResponseEntity<Doctor>(doctor,HttpStatus.NO_CONTENT);
			logger.info("Failed to pull doctor with id: "+doctorId);
		}
		return responseEntity;
	}

	//------POST------
	@PostMapping
	public ResponseEntity<String> saveDoctor(@RequestBody Doctor doctor) {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (doctorService.isDoctorExists(doctor.getDoctor_id())) {
			result = "Doctor (id:"+doctor.getDoctor_id()+") already exists";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
			logger.info("Failed to add doctor with id: "+doctor.getDoctor_id()+", id already exists");
		} else {
			result = doctorService.addDoctor(doctor);
			responseEntity = new ResponseEntity<String>(result,HttpStatus.CREATED);
			logger.info("Added doctor with id: "+doctor.getDoctor_id());
		}
		return responseEntity;
	}

	@PostMapping("/group")
	public ResponseEntity<String> saveDoctors(@RequestBody List<Doctor> doctors) {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		result = doctorService.addDoctors(doctors);
		responseEntity = new ResponseEntity<String>(result,HttpStatus.CREATED);
		logger.info("Added group of doctors with ids: ("+result.substring(result.indexOf(":")+1, result.indexOf(")"))+")");
		return responseEntity;
	}

	//------PUT------
	@PutMapping("{doctorId}")
	public ResponseEntity<String> updateDoctor(@PathVariable("doctorId")int doctorId, @RequestBody Doctor doctor) {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (doctorId != doctor.getDoctor_id()) {
			result = "Doctor (id:"+doctor.getDoctor_id()+") does not match called id:"+doctorId;
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_MODIFIED);
			logger.info("Failed to update doctor with id: "+doctor.getDoctor_id()+" due to mismatch against called id:"+doctorId);
		} else if (doctorService.isDoctorExists(doctor.getDoctor_id())) {
			result = doctorService.updateDoctor(doctorId, doctor);
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
			logger.info("Updated doctor with id: "+doctor.getDoctor_id());
		} else {
			result = "Doctor (id:"+doctor.getDoctor_id()+") does not exist";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_MODIFIED);
			logger.info("Failed to update doctor with id: "+doctor.getDoctor_id()+" since id does not exist");
		}
		return responseEntity;
	}

	//------DELETE------
	@DeleteMapping("{doctorId}")
	public ResponseEntity<String> deleteByDoctorId(@PathVariable("doctorId")int doctorId) {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (doctorService.isDoctorExists(doctorId)) {
			result = doctorService.deleteDoctor(doctorId);
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
			logger.info("Deleted doctor with id: "+doctorId);
		} else {
			result = "Doctor (id:"+doctorId+") does not exist";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_FOUND);
			logger.info("Failed to delete doctor with id: "+doctorId+" since id does not exist");
		}
		return responseEntity;
	}
	
	@DeleteMapping("deleteAll")
	public ResponseEntity<String> deleteAll() {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (doctorService.getDoctors().size() == 0) {
			result = "Table empty, no doctors to delete";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_FOUND);
			logger.info("Failed to delete all doctors due to empty table");
		} else {
			result = doctorService.deleteDoctor();
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
			logger.info("Deleted all doctors");
		}
		return responseEntity;
	}	
}
