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

import com.training.pms.dao.AppointmentDAO;
import com.training.pms.model.Appointment;
import com.training.pms.service.AppointmentService;
import com.training.pms.service.AppointmentServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("appointment")
public class AppointmentController {

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
		return responseEntity;
	}
	
	@GetMapping("{appointmentId}")
	public ResponseEntity<Appointment> getByAppointmentId(@PathVariable("appointmentId")int appointmentId) {
		ResponseEntity<Appointment> responseEntity = null;
		Appointment appointment = new Appointment();
		if (appointmentService.isAppointmentExists(appointmentId)) {
			appointment = appointmentService.getAppointment(appointmentId);
			responseEntity = new ResponseEntity<Appointment>(appointment,HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<Appointment>(appointment,HttpStatus.NO_CONTENT);
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
		} else {
			result = appointmentService.addAppointment(appointment);
			responseEntity = new ResponseEntity<String>(result,HttpStatus.CREATED);
		}
		return responseEntity;
	}

	//------PUT------
	@PutMapping("{appointmentId}")
	public ResponseEntity<String> updateAppointment(@PathVariable("appointmentId")int appointmentId, @RequestBody Appointment appointment) {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (appointmentService.isAppointmentExists(appointment.getAppointmentId())) {
			result = appointmentService.updateAppointment(appointmentId, appointment);
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
		} else {
			result = "Appointment (id:"+appointment.getAppointmentId()+") does not exist";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_MODIFIED);
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
		} else {
			result = "Appointment (id:"+appointmentId+") does not exist";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_FOUND);
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
		} else {
			result = appointmentService.deleteAppointment();
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
		}
		return responseEntity;
	}
	

//Old code from Tufail's demonstration, left here in case we want to see the logic.
	
//	@GetMapping("searchByAppointmentName/{appointmentName}") //localhost:5050/appointment/searchByAppointmentName/Lakme
//	public String getAppointmentByName(@PathVariable("appointmentName")String appointmentName) {
//		return "Getting one appointment by name: "+appointmentName;
//	}
//	
//	@GetMapping("filterByAppointmentPrice/{lowerPrice}/{upperPrice}") //localhost:5050/appointment/filterByAppointmentPrice/250/300
//	public String filterAppointmentByPrice(@PathVariable("lowerPrice") int lowerPrice, @PathVariable("upperPrice") int upperPrice) {
//		if (lowerPrice > upperPrice) {
//			return "First number("+lowerPrice+") cannot be larger than second number("+upperPrice+")";
//		} else {
//			return "Getting appointments in the range: "+lowerPrice+"-"+upperPrice;
//		}
//	}
	

	
}
