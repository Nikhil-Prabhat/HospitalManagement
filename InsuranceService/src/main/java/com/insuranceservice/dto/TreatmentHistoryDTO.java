package com.insuranceservice.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TreatmentHistoryDTO {
	private UUID id;
	private PatientDTO patient;
	private DoctorDTO doctor;
	private String briefDescription;
	private String symptoms;
	private String treatment;
}
