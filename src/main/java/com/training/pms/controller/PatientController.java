package com.training.pms.controller;

import java.util.List;

import org.apache.log4j.Logger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.pms.model.Patient;
import com.training.pms.service.PatientService;
import com.training.pms.service.PatientServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("patient")
public class PatientController {

	private static Logger logger = org.apache.log4j.Logger.getLogger(PatientController.class);
	
	@Autowired
	PatientService patientService = new PatientServiceImpl();

	//------LOGIN------
	@GetMapping("login/{email}/{password}")
	public ResponseEntity<List<Patient>> login(@PathVariable("email") String email, @PathVariable("password") String password) {
		List<Patient> result = patientService.login(email, password);
		ResponseEntity<List<Patient>> responseEntity = null;
		if (result.size() == 0) {
			responseEntity = new ResponseEntity<List<Patient>>(result,HttpStatus.NOT_FOUND);
			logger.info(email+" failed to log in");
		} else {
			responseEntity = new ResponseEntity<List<Patient>>(result,HttpStatus.OK);
			logger.info(email+" logged in");
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
		logger.info("Patient table pulled");
		return responseEntity;
	}
	
	@GetMapping("{patientId}")
	public ResponseEntity<Patient> getByPatientId(@PathVariable("patientId")int patientId) {
		ResponseEntity<Patient> responseEntity = null;
		Patient patient = new Patient();
		if (patientService.isPatientExists(patientId)) {
			patient = patientService.getPatient(patientId);
			responseEntity = new ResponseEntity<Patient>(patient,HttpStatus.OK);
			logger.info("Pulled patient with id: "+patientId);
		} else {
			responseEntity = new ResponseEntity<Patient>(patient,HttpStatus.NO_CONTENT);
			logger.info("Failed to pull patient with id: "+patientId);
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
			logger.info("Failed to add patient with id: "+patient.getPatient_id()+", id already exists");
		} else {
			result = patientService.addPatient(patient);
			responseEntity = new ResponseEntity<String>(result,HttpStatus.CREATED);
			logger.info("Added patient with id: "+patient.getPatient_id());
		}
		return responseEntity;
	}

	@PostMapping("/group")
	public ResponseEntity<String> savePatients(@RequestBody List<Patient> patients) {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		result = patientService.addPatients(patients);
		responseEntity = new ResponseEntity<String>(result,HttpStatus.CREATED);
		logger.info("Added group of patients with ids: ("+result.substring(result.indexOf(":")+1, result.indexOf(")"))+")");
		return responseEntity;
	}

	//------PUT------
	@PutMapping("{patientId}")
	public ResponseEntity<String> updatePatient(@PathVariable("patientId")int patientId, @RequestBody Patient patient) {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (patientId != patient.getPatient_id()) {
			result = "Patient (id:"+patient.getPatient_id()+") does not match called id:"+patientId;
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_MODIFIED);
			logger.info("Failed to update patient with id: "+patient.getPatient_id()+" due to mismatch against called id:"+patientId);
		} else if (patientService.isPatientExists(patient.getPatient_id())) {
			result = patientService.updatePatient(patientId, patient);
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
			logger.info("Updated patient with id: "+patient.getPatient_id());
		} else {
			result = "Patient (id:"+patient.getPatient_id()+") does not exist";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_MODIFIED);
			logger.info("Failed to update patient with id: "+patient.getPatient_id()+" since id does not exist");
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
			logger.info("Deleted patient with id: "+patientId);
		} else {
			result = "Patient (id:"+patientId+") does not exist";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_FOUND);
			logger.info("Failed to delete patient with id: "+patientId+" since id does not exist");
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
			logger.info("Failed to delete all patients due to empty table");
		} else {
			result = patientService.deletePatient();
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
			logger.info("Deleted all patients");
		}
		return responseEntity;
	}
	
}
