package com.hospitalservice.dto;

import java.util.Set;

import com.hospitalservice.entity.TreatmentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientDTO {

	private String name;
	private String mobileNo;
	private String address;
	private String briefProblemDescription;
	private TreatmentStatus treatmentStatus;
	Set<DoctorDTO> doctors;

}
