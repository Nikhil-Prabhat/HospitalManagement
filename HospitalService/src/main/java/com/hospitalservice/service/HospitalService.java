package com.hospitalservice.service;

import java.util.UUID;

import com.hospitalservice.dto.DoctorDTO;
import com.hospitalservice.dto.PatientDTO;
import com.hospitalservice.entity.Doctor;
import com.hospitalservice.entity.Patient;

public interface HospitalService {

	public void saveDoctor(DoctorDTO doctorDTO);

	public Doctor getDoctorById(UUID idOfDoctor);

	public void updateDoctor(UUID idOfDoctor, DoctorDTO doctorDTO);
	
	public void deleteDoctor(UUID idOfDoctor);

	public void savePatient(PatientDTO patientDTO);

	public Patient getPatientById(UUID idOfPatient);

	public void updatePatient(UUID idOfPatient, PatientDTO patientDTO);
	
	public void deletePatient(UUID idOfPatient);

	public void assignPatientToDoctor(UUID idOfDoctor, UUID idOfPatient);
	

}
