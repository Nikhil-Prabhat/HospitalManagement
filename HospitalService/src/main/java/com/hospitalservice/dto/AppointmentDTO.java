package com.hospitalservice.dto;

import java.util.Date;

import com.hospitalservice.entity.AppointmentStatus;
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
public class AppointmentDTO implements Constants {

	@NotNull(message = NOT_NULL)
	@NotEmpty(message = NOT_EMPTY)
	private String patientName;

	@NotNull(message = NOT_NULL)
	@NotEmpty(message = NOT_EMPTY)
	@Size(min = 10, max = 10, message = MOBILE_VALIDATION_MSG)
	private String patientMobileNo;

	@NotNull(message = NOT_NULL)
	@NotEmpty(message = NOT_EMPTY)
	private String doctorAssignedName;

	@NotNull(message = NOT_NULL)
	private Date appointmentDate;
	private AppointmentStatus appointmentStatus;

}
