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
@Table(name = "patient_info")
public class Patient {
	@Id
	private int patientId;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private String sex;
	private String birthdate;
	private String address;
	private String city;
	private String state;
	private int zip_code;
	private String date_created;
}
