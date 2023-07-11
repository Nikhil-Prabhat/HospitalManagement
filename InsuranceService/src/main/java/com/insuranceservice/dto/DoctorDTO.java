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
public class DoctorDTO {

	private UUID id;
	private String name;
	private String specialisation;
	private String mobileNo;
	private String address;
	
	@JsonIgnore
	private Set<PatientDTO> patients;
}
