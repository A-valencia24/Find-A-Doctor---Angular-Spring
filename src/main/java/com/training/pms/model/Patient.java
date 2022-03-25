package com.training.pms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "jwapatients")
public class Patient {
	@Id
	private int patientId;
	private String patientName;
	private String symptom;
	private int billAmount;
	private String doctorAttended;

}