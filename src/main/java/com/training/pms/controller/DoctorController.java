package com.training.pms.controller;

import java.util.List;
import java.util.Optional;

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
import com.training.pms.model.Patient;
import com.training.pms.service.DoctorService;
import com.training.pms.service.DoctorServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("doctor")
public class DoctorController {
	
	@Autowired
	DoctorService doctorService = new DoctorServiceImpl();

	//------LOGIN------
	@GetMapping("login/{email}/{password}")
	public ResponseEntity<List<Doctor>> login(@PathVariable("email") String email, @PathVariable("password") String password) {
		List<Doctor> result = doctorService.login(email, password);
		ResponseEntity<List<Doctor>> responseEntity = null;
		if (result.size() == 0) {
			responseEntity = new ResponseEntity<List<Doctor>>(result,HttpStatus.NOT_FOUND);
		} else if (result.size() > 1) {
			//Should not be possible,
			//only happens if table contains multiple entries with the same email & password
			responseEntity = new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			responseEntity = new ResponseEntity<List<Doctor>>(result,HttpStatus.OK);
		}
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
		return responseEntity;
	}
	
	@GetMapping("{doctorId}")
	public ResponseEntity<Doctor> getByDoctorId(@PathVariable("doctorId")int doctorId) {
		ResponseEntity<Doctor> responseEntity = null;
		Doctor doctor = new Doctor();
		if (doctorService.isDoctorExists(doctorId)) {
			doctor = doctorService.getDoctor(doctorId);
			responseEntity = new ResponseEntity<Doctor>(doctor,HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<Doctor>(doctor,HttpStatus.NO_CONTENT);
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
		} else {
			result = doctorService.addDoctor(doctor);
			responseEntity = new ResponseEntity<String>(result,HttpStatus.CREATED);
		}
		return responseEntity;
	}

	//------PUT------
	@PutMapping("{doctorId}")
	public ResponseEntity<String> updateDoctor(@PathVariable("doctorId")int doctorId, @RequestBody Doctor doctor) {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (doctorService.isDoctorExists(doctor.getDoctor_id())) {
			result = doctorService.updateDoctor(doctorId, doctor);
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
		} else {
			result = "Doctor (id:"+doctor.getDoctor_id()+") does not exist";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_MODIFIED);
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
		} else {
			result = "Doctor (id:"+doctorId+") does not exist";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_FOUND);
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
		} else {
			result = doctorService.deleteDoctor();
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
		}
		return responseEntity;
	}
	

//Old code from Tufail's demonstration, left here in case we want to see the logic.
	
//	@GetMapping("searchByDoctorName/{doctorName}") //localhost:5050/doctor/searchByDoctorName/Lakme
//	public String getDoctorByName(@PathVariable("doctorName")String doctorName) {
//		return "Getting one doctor by name: "+doctorName;
//	}
//	
//	@GetMapping("filterByDoctorPrice/{lowerPrice}/{upperPrice}") //localhost:5050/doctor/filterByDoctorPrice/250/300
//	public String filterDoctorByPrice(@PathVariable("lowerPrice") int lowerPrice, @PathVariable("upperPrice") int upperPrice) {
//		if (lowerPrice > upperPrice) {
//			return "First number("+lowerPrice+") cannot be larger than second number("+upperPrice+")";
//		} else {
//			return "Getting doctors in the range: "+lowerPrice+"-"+upperPrice;
//		}
//	}
	

	
}
