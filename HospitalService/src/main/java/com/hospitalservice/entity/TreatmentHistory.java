package com.hospitalservice.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TREATMENT_HISTORY_TABLE", schema = "HOSPITAL")
public class TreatmentHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "PATIENT_TH_FK")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "DOCTOR_TH_FK")
	private Doctor doctor;
	private String briefDescription;
	private String symptoms;
	private String treatment;
	
}
