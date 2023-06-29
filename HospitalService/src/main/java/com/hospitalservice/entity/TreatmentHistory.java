package com.hospitalservice.entity;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TREATMENT_HISTORY_TABLE", schema = "HOSPITAL")
public class TreatmentHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PATIENT_ID_FK")
	private Patient patient;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DOCTOR_ID_FK")
	private Doctor doctor;
	
	private String briefDescription;
	private String symptoms;
	private String treatment;
	
}
