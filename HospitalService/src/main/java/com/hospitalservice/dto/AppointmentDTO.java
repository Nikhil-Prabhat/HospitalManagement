package com.hospitalservice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentDTO {

	private String patientName;
	private String patientMobileNo;
	private String doctorAssigned;
	private Date appointmentDate;
	private String appointmentStatus;

}
