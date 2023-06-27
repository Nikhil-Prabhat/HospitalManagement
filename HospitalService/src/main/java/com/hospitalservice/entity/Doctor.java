package com.hospitalservice.entity;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "DOCTOR_TABLE", schema = "HOSPITAL")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String name;
	private String specialisation;
	private String mobileNo;
	private String address;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "DOCTOR_PATIENT_TABLE", schema = "HOSPITAL", 
	joinColumns = {
			@JoinColumn(name = "DOCTOR_ID", referencedColumnName = "ID") }, 
	inverseJoinColumns = {
			@JoinColumn(name = "PATIENT_ID", referencedColumnName = "ID")
					}
	)
	private Set<Patient> patients;
}
