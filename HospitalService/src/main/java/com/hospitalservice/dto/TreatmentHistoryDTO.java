package com.hospitalservice.dto;

import com.hospitalservice.entity.Doctor;
import com.hospitalservice.entity.Patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TreatmentHistoryDTO {

	private Patient patient;
	private Doctor doctor;
	private String briefDescription;
	private String symptoms;
	private String treatment;

}
