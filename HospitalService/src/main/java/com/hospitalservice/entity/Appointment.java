package com.hospitalservice.entity;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "APPOINTMENT_TABLE", schema = "HOSPITAL")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String patientName;
	private String patientMobileNo;
	private String doctorAssigned;
	private Date appointmentDate;
	private String appointmentStatus;
}
