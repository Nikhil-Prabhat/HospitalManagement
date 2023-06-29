package com.hospitalservice.dto;

import com.hospitalservice.entity.Doctor;
import com.hospitalservice.entity.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TreatmentHistoryDTO {
	
	private Patient patient;
	private Doctor doctor;
	private String briefDescription;
	private String symptoms;
	private String treatment;
	
}
