package com.training.pms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "appointments")
public class Appointment {
	@Id
	@GeneratedValue
	private int appointmentId;
	private int patientId;
	private int doctorId;
	private String date;
	private int timeSlot;
	private String insurance;
	private String description;
}
