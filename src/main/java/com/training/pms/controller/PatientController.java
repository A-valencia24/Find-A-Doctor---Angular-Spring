package com.training.pms.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.pms.model.Patient;

@RestController
@RequestMapping("patient")
public class PatientController {

	@GetMapping //localhost:5050/patient
	public String getPatients() {
		return "Getting all patients";
	}
	
	@GetMapping("single") //localhost:5050/patient/single
	public String getPatient() {
		return "Getting one patient";
	}
	
	@GetMapping("searchByPatientId/{patientId}") //localhost:5050/patient/searchByPatientId/52
	public String getPatientById(@PathVariable("patientId")int patientId) {
		return "Getting one patient by patient id: "+patientId;
	}
	
	@GetMapping("searchByPatientName/{patientName}") //localhost:5050/patient/searchByPatientName/Lakme
	public String getPatientByName(@PathVariable("patientName")String patientName) {
		return "Getting one patient by name: "+patientName;
	}
	
	@GetMapping("filterByPatientBillAmount/{lowerBill}/{upperBill}") //localhost:5050/patient/filterByPatientPrice/250/300
	public String filterPatientByPrice(@PathVariable("lowerBill") int lowerBill, @PathVariable("upperBill") int upperBill) {
		if (lowerBill > upperBill) {
			return "First number("+lowerBill+") cannot be larger than second number("+upperBill+")";
		} else {
			return "Getting patients by bills in range: "+lowerBill+"-"+upperBill;
		}
	}
	
	@DeleteMapping("deleteByPatientId/{patientId}")
	public String deleteByPatientId(@PathVariable("patientId")int patientId) {
		return "Deleted patient with Id: "+patientId;
	}
	
	@DeleteMapping("deleteByPatientName/{patientName}")
	public String deleteByPatientName(@PathVariable("patientName")String patientName) {
		return "Deleted all patients with name: "+patientName;
	}
	
	
	
	@PostMapping
	public String savePatient(@RequestBody Patient patient) {
		return "Save a single patient: "+patient;
	}
	
	@PutMapping("{patientId}")
	public String updatePatient(@PathVariable("patientId")int patientId, @RequestBody Patient patient) {
		return "Update a single patient with id: "+patientId+" and the changes are "+patient;
	}
	
}
