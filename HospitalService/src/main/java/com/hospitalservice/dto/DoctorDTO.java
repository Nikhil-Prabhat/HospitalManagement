package com.hospitalservice.dto;

import java.util.Set;

import com.hospitalservice.util.Constants;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorDTO implements Constants{

	@NotNull(message = NOT_NULL)
	@NotEmpty(message = NOT_EMPTY)
	private String name;
	
	@NotNull(message = NOT_NULL)
	@NotEmpty(message = NOT_EMPTY)
	private String specialisation;
	
	@NotNull(message = NOT_NULL)
	@NotEmpty(message = NOT_EMPTY)
	@Size(min = 10, max = 10, message = MOBILE_VALIDATION_MSG)
	private String mobileNo;
	
	@NotNull(message = NOT_NULL)
	@NotEmpty(message = NOT_EMPTY)
	private String address;
	private Set<PatientDTO> patients;
}
