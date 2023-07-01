package com.hospitalservice.entity;

import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "PATIENT_TABLE", schema = "HOSPITAL")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String name;
	private String mobileNo;
	private String address;
	private String briefProblemDescription;

	@Enumerated(EnumType.STRING)
	private TreatmentStatus treatmentStatus;

	@ManyToMany(mappedBy = "patients", fetch = FetchType.LAZY)
	@JsonBackReference
	private Set<Doctor> doctors;
}
