package com.hospitalservice.dto;

import java.util.Date;

import com.hospitalservice.entity.AppointmentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentDTO {

	private String patientName;
	private String patientMobileNo;
	private String doctorAssignedName;
	private Date appointmentDate;
	private AppointmentStatus appointmentStatus;

}
