package com.insuranceservice.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientClaimDTO {
	
	private UUID patientId;
	private String insurerName;
	private String insurerAmountLimit;
	private String insuranceType;
	private Double amountSpent;
}
