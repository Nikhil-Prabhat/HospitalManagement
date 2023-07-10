package com.insuranceservice.entity;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientClaim {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String patientId;
	private Double amountSpent;
	private Double remainingAmount;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "PATIENT_INSURANCE_FK" ,referencedColumnName = "ID")
	private Insurance insurance;
	
	

}
