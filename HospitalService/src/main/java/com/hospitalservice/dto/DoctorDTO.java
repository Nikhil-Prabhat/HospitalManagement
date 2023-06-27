package com.hospitalservice.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorDTO {

	private String name;
	private String specialisation;
	private String mobileNo;
	private String address;
	private Set<PatientDTO> patients;
}
