package com.hospitalservice.dto;

import com.hospitalservice.entity.Doctor;
import com.hospitalservice.entity.Patient;
import com.hospitalservice.util.Constants;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TreatmentHistoryDTO implements Constants {

	@NotNull(message = NOT_NULL)
	private Patient patient;
	
	@NotNull(message = NOT_NULL)
	private Doctor doctor;
	
	@NotNull(message = NOT_NULL)
	@NotEmpty(message = NOT_EMPTY)
	private String briefDescription;
	
	@NotNull(message = NOT_NULL)
	@NotEmpty(message = NOT_EMPTY)
	private String symptoms;
	
	@NotNull(message = NOT_NULL)
	@NotEmpty(message = NOT_EMPTY)
	private String treatment;

}
