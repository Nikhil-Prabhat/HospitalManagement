package com.insuranceservice.dto;

import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientDTO {
	private UUID id;
	private String name;
	private String mobileNo;
	private String address;
	private String briefProblemDescription;
	private TreatmentStatus treatmentStatus;
	
	@JsonIgnore
	private Set<DoctorDTO> doctors;
}
