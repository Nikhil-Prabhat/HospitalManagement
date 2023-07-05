package com.hospitalservice.dto;

import com.hospitalservice.entity.Patient;
import com.hospitalservice.util.Constants;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BillDTO implements Constants {
	
	@NotNull(message = NOT_NULL)
	private Patient patient;
	
	@NotNull(message = NOT_NULL)
	private Double consultationFee;
	
	@NotNull(message = NOT_NULL)
	private Double pharmacyFee;
	
	@NotNull(message = NOT_NULL)
	private Double hospitalizationFee;
	
	private Double totalAmountOfBill;
}
