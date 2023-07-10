package com.insuranceservice.dto;

import java.util.Set;
import java.util.UUID;

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
	private Set<DoctorDTO> doctors;
}
