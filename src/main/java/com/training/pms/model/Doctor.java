package com.training.pms.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "doctors")
public class Doctor {
	//using SQL naming format "patient_id" instead of java "patientId" for conformity across platforms.
	//If java naming format is used, SQL automatically converts to it's format
	@Id
	@GeneratedValue
	private int doctor_id;
	private String firstname;
	private String lastname;
	@Column(unique = true)
	private String email;
	private String password;
	private long phone;
	private String sex;
	private String birthdate;
	private String address;
	private String city;
	private String state;
	private int zip_code;
	private String weekly_availability;
	private String specialty;
	private int rating;
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date date_created;
}