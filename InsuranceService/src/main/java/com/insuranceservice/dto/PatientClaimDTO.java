package com.insuranceservice.dto;

import java.util.UUID;

import com.insuranceservice.entity.Insurance;
import com.insuranceservice.util.Constants;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientClaimDTO implements Constants {
	
	private UUID patientId;
	
	@NotNull(message = NOT_NULL)
	private Insurance insuranceTaken;
	
	private Double amountSpent;
	private Double remainingAmount;
}
