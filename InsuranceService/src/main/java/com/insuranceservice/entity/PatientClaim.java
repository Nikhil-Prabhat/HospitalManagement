package com.insuranceservice.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "PATIENT_CLAIM_TABLE", schema = "INSURANCE")
public class PatientClaim {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private UUID patientId;
	private String insurerName;
	private String insurerAmountLimit;
	private String insuranceType;
	private Double amountSpent;
}
