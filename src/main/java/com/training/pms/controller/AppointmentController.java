package com.training.pms.controller;

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

import com.training.pms.dao.AppointmentDAO;
import com.training.pms.model.Appointment;
import com.training.pms.model.Patient;
import com.training.pms.service.AppointmentService;
import com.training.pms.service.AppointmentServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("appointment")
public class AppointmentController {

	private static Logger logger = org.apache.log4j.Logger.getLogger(AppointmentController.class);

	@Autowired
	AppointmentDAO appointmentDAO;
	@Autowired
	AppointmentService appointmentService = new AppointmentServiceImpl();

	//------GET------
	@GetMapping
	public ResponseEntity<List<Appointment>> getAppointments() {
		List<Appointment> result = appointmentService.getAppointments();
		ResponseEntity<List<Appointment>> responseEntity = null;
		if (result.size() == 0) {
			responseEntity = new ResponseEntity<List<Appointment>>(result,HttpStatus.NO_CONTENT);
		} else {
			responseEntity = new ResponseEntity<List<Appointment>>(result,HttpStatus.OK);
		}
		logger.info("Appointment table pulled");
		return responseEntity;
	}
	
	@GetMapping("{appointmentId}")
	public ResponseEntity<Appointment> getByAppointmentId(@PathVariable("appointmentId")int appointmentId) {
		ResponseEntity<Appointment> responseEntity = null;
		Appointment appointment = new Appointment();
		if (appointmentService.isAppointmentExists(appointmentId)) {
			appointment = appointmentService.getAppointment(appointmentId);
			responseEntity = new ResponseEntity<Appointment>(appointment,HttpStatus.OK);
			logger.info("Pulled appointment with id: "+appointmentId);
		} else {
			responseEntity = new ResponseEntity<Appointment>(appointment,HttpStatus.NO_CONTENT);
			logger.info("Failed to pull appointment with id: "+appointmentId);
		}
		return responseEntity;
	}
	
	@GetMapping("patient/{patientId}")
	public ResponseEntity<List<Appointment>> getByPatientId(@PathVariable("patientId")int patientId) {
		List<Appointment> result = appointmentDAO.findByPatientId(patientId);
		ResponseEntity<List<Appointment>> responseEntity = null;
		if (result.size() == 0) {
			responseEntity = new ResponseEntity<List<Appointment>>(result,HttpStatus.NO_CONTENT);
		} else {
			responseEntity = new ResponseEntity<List<Appointment>>(result,HttpStatus.OK);
		}
		logger.info("Searched for appointments with patient id:"+patientId+", found ("+result.size()+")");
		return responseEntity;
	}
	
	@GetMapping("doctor/{doctorId}")
	public ResponseEntity<List<Appointment>> getByDoctorId(@PathVariable("doctorId")int doctorId) {
		List<Appointment> result = appointmentDAO.findByDoctorId(doctorId);
		ResponseEntity<List<Appointment>> responseEntity = null;
		if (result.size() == 0) {
			responseEntity = new ResponseEntity<List<Appointment>>(result,HttpStatus.NO_CONTENT);
		} else {
			responseEntity = new ResponseEntity<List<Appointment>>(result,HttpStatus.OK);
		}
		logger.info("Searched for appointments with patient id:"+doctorId+", found ("+result.size()+")");
		return responseEntity;
	}

	//------POST------
	@PostMapping
	public ResponseEntity<String> saveAppointment(@RequestBody Appointment appointment) {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (appointmentService.isAppointmentExists(appointment.getAppointmentId())) {
			result = "Appointment (id:"+appointment.getAppointmentId()+") already exists";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
			logger.info("Failed to add appointment with id: "+appointment.getAppointmentId()+", id already exists");
		} else {
			result = appointmentService.addAppointment(appointment);
			responseEntity = new ResponseEntity<String>(result,HttpStatus.CREATED);
			logger.info("Added appointment with id: "+appointment.getAppointmentId());
		}
		return responseEntity;
	}

	@PostMapping("/group")
	public ResponseEntity<String> saveAppointments(@RequestBody List<Appointment> appointments) {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		result = appointmentService.addAppointments(appointments);
		responseEntity = new ResponseEntity<String>(result,HttpStatus.CREATED);
		logger.info("Added group of appointments with ids: ("+result.substring(result.indexOf(":")+1, result.indexOf(")"))+")");
		return responseEntity;
	}


	//------PUT------
	@PutMapping("{appointmentId}")
	public ResponseEntity<String> updateAppointment(@PathVariable("appointmentId")int appointmentId, @RequestBody Appointment appointment) {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (appointmentId != appointment.getAppointmentId()) {
			result = "Appointment (id:"+appointment.getAppointmentId()+") does not match called id:"+appointmentId;
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_MODIFIED);
			logger.info("Failed to update appointment with id: "+appointment.getAppointmentId()+" due to mismatch against called id:"+appointmentId);
		} else if (appointmentService.isAppointmentExists(appointment.getAppointmentId())) {
			result = appointmentService.updateAppointment(appointmentId, appointment);
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
			logger.info("Updated appointment with id: "+appointment.getAppointmentId());
		} else {
			result = "Appointment (id:"+appointment.getAppointmentId()+") does not exist";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_MODIFIED);
			logger.info("Failed to update appointment with id: "+appointment.getAppointmentId()+" since id does not exist");
		}
		return responseEntity;
	}

	//------DELETE------
	@DeleteMapping("{appointmentId}")
	public ResponseEntity<String> deleteByAppointmentId(@PathVariable("appointmentId")int appointmentId) {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (appointmentService.isAppointmentExists(appointmentId)) {
			result = appointmentService.deleteAppointment(appointmentId);
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
			logger.info("Deleted appointment with id: "+appointmentId);
		} else {
			result = "Appointment (id:"+appointmentId+") does not exist";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_FOUND);
			logger.info("Failed to delete appointment with id: "+appointmentId+" since id does not exist");
		}
		return responseEntity;
	}
	
	@DeleteMapping("deleteAll")
	public ResponseEntity<String> deleteAll() {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (appointmentService.getAppointments().size() == 0) {
			result = "Table empty, no appointments to delete";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_FOUND);
			logger.info("Failed to delete all appointments due to empty table");
		} else {
			result = appointmentService.deleteAppointment();
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
			logger.info("Deleted all appointments");
		}
		return responseEntity;
	}	
}
