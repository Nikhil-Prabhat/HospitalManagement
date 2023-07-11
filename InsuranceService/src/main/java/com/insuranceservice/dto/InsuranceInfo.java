package com.insuranceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsuranceInfo {

	private String patientName;
	private String patientAddress;
	private String patientMobileNumber;
	private String insurerName;
	private String insurerAmountLimit;
	private String insuranceType;
	private Double pharmacyFee;
	private Double hospitalizationFee;
	private Double totalAmountOfBill;
	private Double remainingInsuranceAmount;
}
