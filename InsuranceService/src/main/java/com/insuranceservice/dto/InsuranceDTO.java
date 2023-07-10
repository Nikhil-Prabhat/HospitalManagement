package com.insuranceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsuranceDTO {

	private String insurerName;
	private Double insurerAmountLimit;
	private Integer disbursementTime;
	private String insurerOriginState;
	private String insurerOriginCountry;
	private String insuranceType;
}
