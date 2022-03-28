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

import com.training.pms.dao.PatientDAO;
import com.training.pms.model.Patient;
import com.training.pms.service.PatientService;
import com.training.pms.service.PatientServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("patient")
public class PatientController {
	
	@Autowired
	PatientService patientService = new PatientServiceImpl();

	//------LOGIN------
	@GetMapping("login/{email}/{password}")
	public ResponseEntity<List<Patient>> login(@PathVariable("email") String email, @PathVariable("password") String password) {
		List<Patient> result = patientService.login(email, password);
		ResponseEntity<List<Patient>> responseEntity = null;
		if (result.size() == 0) {
			responseEntity = new ResponseEntity<List<Patient>>(result,HttpStatus.NOT_FOUND);
		} else if (result.size() > 1) {
			//Should not be possible,
			//only happens if table contains multiple entries with the same email & password
			responseEntity = new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			responseEntity = new ResponseEntity<List<Patient>>(result,HttpStatus.OK);
		}
		return responseEntity;
	}

	//------GET------
	@GetMapping
	public ResponseEntity<List<Patient>> getPatients() {
		List<Patient> result = patientService.getPatients();
		ResponseEntity<List<Patient>> responseEntity = null;
		if (result.size() == 0) {
			responseEntity = new ResponseEntity<List<Patient>>(result,HttpStatus.NO_CONTENT);
		} else {
			responseEntity = new ResponseEntity<List<Patient>>(result,HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("{patientId}")
	public ResponseEntity<Patient> getByPatientId(@PathVariable("patientId")int patientId) {
		ResponseEntity<Patient> responseEntity = null;
		Patient patient = new Patient();
		if (patientService.isPatientExists(patientId)) {
			patient = patientService.getPatient(patientId);
			responseEntity = new ResponseEntity<Patient>(patient,HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<Patient>(patient,HttpStatus.NO_CONTENT);
		}
		return responseEntity;
	}

	//------POST------
	@PostMapping
	public ResponseEntity<String> savePatient(@RequestBody Patient patient) {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (patientService.isPatientExists(patient.getPatient_id())) {
			result = "Patient (id:"+patient.getPatient_id()+") already exists";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
		} else {
			result = patientService.addPatient(patient);
			responseEntity = new ResponseEntity<String>(result,HttpStatus.CREATED);
		}
		return responseEntity;
	}

	//------PUT------
	@PutMapping("{patientId}")
	public ResponseEntity<String> updatePatient(@PathVariable("patientId")int patientId, @RequestBody Patient patient) {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (patientService.isPatientExists(patient.getPatient_id())) {
			result = patientService.updatePatient(patientId, patient);
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
		} else {
			result = "Patient (id:"+patient.getPatient_id()+") does not exist";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_MODIFIED);
		}
		return responseEntity;
	}

	//------DELETE------
	@DeleteMapping("{patientId}")
	public ResponseEntity<String> deleteByPatientId(@PathVariable("patientId")int patientId) {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (patientService.isPatientExists(patientId)) {
			result = patientService.deletePatient(patientId);
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
		} else {
			result = "Patient (id:"+patientId+") does not exist";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	@DeleteMapping("deleteAll")
	public ResponseEntity<String> deleteAll() {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (patientService.getPatients().size() == 0) {
			result = "Table empty, no patients to delete";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_FOUND);
		} else {
			result = patientService.deletePatient();
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
		}
		return responseEntity;
	}
	

//Old code from Tufail's demonstration, left here in case we want to see the logic.
	
//	@GetMapping("searchByPatientName/{patientName}") //localhost:5050/patient/searchByPatientName/Lakme
//	public String getPatientByName(@PathVariable("patientName")String patientName) {
//		return "Getting one patient by name: "+patientName;
//	}
//	
//	@GetMapping("filterByPatientPrice/{lowerPrice}/{upperPrice}") //localhost:5050/patient/filterByPatientPrice/250/300
//	public String filterPatientByPrice(@PathVariable("lowerPrice") int lowerPrice, @PathVariable("upperPrice") int upperPrice) {
//		if (lowerPrice > upperPrice) {
//			return "First number("+lowerPrice+") cannot be larger than second number("+upperPrice+")";
//		} else {
//			return "Getting patients in the range: "+lowerPrice+"-"+upperPrice;
//		}
//	}
	

	
}
