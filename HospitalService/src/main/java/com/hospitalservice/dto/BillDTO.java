package com.hospitalservice.dto;

import com.hospitalservice.entity.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BillDTO {
	
	private Patient patient;
	private Double consultationFee;
	private Double pharmacyFee;
	private Double hospitalizationFee;
	private Double totalAmountOfBill;
}
