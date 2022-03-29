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
	//using SQL naming format "patient_id" instead of java "patientId" for conformity across platforms.
	//If java naming format is used, SQL automatically converts to it's format
	@Id
	@GeneratedValue
	private int appointment_id;
	private int patient_id;
	private int doctor_id;
	private String date;
	private int time_slot;
	private String insurance;
	private String description;
}
