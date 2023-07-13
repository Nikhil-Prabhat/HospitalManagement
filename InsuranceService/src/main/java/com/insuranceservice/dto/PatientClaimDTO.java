package com.insuranceservice.dto;

import java.util.UUID;

import com.insuranceservice.entity.Insurance;
import com.insuranceservice.util.Constants;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientClaimDTO implements Constants {
	
	@NotNull(message = NOT_NULL)
	@NotEmpty(message = NOT_EMPTY)
	private UUID patientId;
	
	@NotNull(message = NOT_NULL)
	private Insurance insuranceTaken;
	
	@NotNull(message = NOT_NULL)
	@NotEmpty(message = NOT_EMPTY)
	private Double amountSpent;
	
	@NotNull(message = NOT_NULL)
	@NotEmpty(message = NOT_EMPTY)
	private Double remainingAmount;
}
