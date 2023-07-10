package com.insuranceservice.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BillDTO {
	
	private UUID id;
	private PatientDTO patient;
	private Double consultationFee;
	private Double pharmacyFee;
	private Double hospitalizationFee;
	private Double totalAmountOfBill;

}
