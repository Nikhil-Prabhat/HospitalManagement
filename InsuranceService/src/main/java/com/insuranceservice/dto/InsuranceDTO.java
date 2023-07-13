package com.insuranceservice.dto;

import com.insuranceservice.util.Constants;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsuranceDTO implements Constants{

	@NotNull(message = NOT_NULL)
	@NotEmpty(message = NOT_EMPTY)
	private String insurerName;
	
	@NotNull(message = NOT_NULL)
	@NotEmpty(message = NOT_EMPTY)
	private Double insurerAmountLimit;
	
	@NotNull(message = NOT_NULL)
	@NotEmpty(message = NOT_EMPTY)
	private Integer disbursementTime;
	
	private String insurerOriginState;
	private String insurerOriginCountry;
	
	@NotNull(message = NOT_NULL)
	@NotEmpty(message = NOT_EMPTY)
	@Pattern(regexp = INSURANCE_TYPE_CHECK, message = INSURANCE_TYPE_NOT_PROVIDED_CORRECTLY)
	private String insuranceType;
}
