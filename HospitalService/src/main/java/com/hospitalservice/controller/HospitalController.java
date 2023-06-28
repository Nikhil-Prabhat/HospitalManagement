package com.hospitalservice.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitalservice.dto.AppointmentDTO;
import com.hospitalservice.dto.DoctorDTO;
import com.hospitalservice.dto.PatientDTO;
import com.hospitalservice.entity.Appointment;
import com.hospitalservice.entity.Doctor;
import com.hospitalservice.entity.Patient;
import com.hospitalservice.service.HospitalService;
import com.hospitalservice.util.Constants;

@RestController
@RequestMapping(Constants.HOSPITAL_APP)
public class HospitalController implements Constants {

	@Autowired
	private HospitalService hospitalServiceImpl;

	/* CRUD Endpoints Pertaining to Doctor */
	
	@PostMapping(SAVE_DOCTOR)
	public ResponseEntity<?> saveDoctors(@RequestBody DoctorDTO doctorDTO) {
		hospitalServiceImpl.saveDoctor(doctorDTO);
		return new ResponseEntity<>(DOCTOR_SAVED_SUCCESSFULLY, HttpStatus.CREATED);
	}

	@GetMapping(GET_DOCTOR_BY_ID)
	public ResponseEntity<?> getDoctorById(@PathVariable(name = ID) UUID idOfDoctor) {
		Doctor doctorById = hospitalServiceImpl.getDoctorById(idOfDoctor);
		return new ResponseEntity<>(doctorById, HttpStatus.OK);
	}

	@PutMapping(UPDATE_DOCTOR)
	public ResponseEntity<?> updateDoctor(@PathVariable(name = ID) UUID idOfDoctor, @RequestBody DoctorDTO doctorDTO) {
		hospitalServiceImpl.updateDoctor(idOfDoctor, doctorDTO);
		return new ResponseEntity<>(DOCTOR_UPDATED_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(DELETE_DOCTOR)
	public ResponseEntity<?> deleteDoctor(@PathVariable(name = ID) UUID idOfDoctor) {
		hospitalServiceImpl.deleteDoctor(idOfDoctor);
		return new ResponseEntity<>(DOCTOR_DELETED_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

	/* CRUD Endpoints Pertaining to Patients */
	
	@PostMapping(SAVE_PATIENT)
	public ResponseEntity<?> savePatients(@RequestBody PatientDTO patientDTO) {
		hospitalServiceImpl.savePatient(patientDTO);
		return new ResponseEntity<>(PATIENT_SAVED_SUCCESSFULLY, HttpStatus.CREATED);
	}

	@GetMapping(GET_PATIENT_BY_ID)
	public ResponseEntity<?> getPatientById(@PathVariable(name = ID) UUID idOfPatient) {
		Patient patientById = hospitalServiceImpl.getPatientById(idOfPatient);
		return new ResponseEntity<>(patientById, HttpStatus.OK);
	}

	@PutMapping(UPDATE_PATIENT)
	public ResponseEntity<?> updatePatient(@PathVariable(name = ID) UUID idOfPatient, PatientDTO patientDTO) {
		hospitalServiceImpl.updatePatient(idOfPatient, patientDTO);
		return new ResponseEntity<>(PATIENT_UPDATED_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(DELETE_PATIENT)
	public ResponseEntity<?> deletePatient(@PathVariable(name = ID) UUID idOfPatient) {
		hospitalServiceImpl.deletePatient(idOfPatient);
		return new ResponseEntity<>(PATIENT_DELETED_SUCCESFULLY, HttpStatus.ACCEPTED);
	}

	
	/* Endpoint to assign patient to a doctor */
	
	@PutMapping(ASSIGN_PATIENT_TO_DOCTOR)
	public ResponseEntity<?> assignPatientToDoctor(@PathVariable(name = DOCTOR_ID) UUID idOfDoctor,
			@PathVariable(name = PATIENT_ID) UUID idOfPatient) {
		hospitalServiceImpl.assignPatientToDoctor(idOfDoctor, idOfPatient);
		return new ResponseEntity<>(ASSIGN_PATIENT_TO_DOCTOR_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

	/* CRUD Endpoints related to appointment*/
	
	@PostMapping(SAVE_APPOINTMENT)
	public ResponseEntity<?> saveAppointment(@RequestBody AppointmentDTO appointmentDTO) {
		hospitalServiceImpl.saveAppointment(appointmentDTO);
		return new ResponseEntity<>(APPOINTMENT_SAVED_SUCCESSFULLY, HttpStatus.CREATED);
	}

	@GetMapping(GET_APPOINTMENT_BY_ID)
	public ResponseEntity<?> getAppointmentById(@PathVariable(name = ID) UUID idOfAppointment) {
		Appointment appointmentById = hospitalServiceImpl.getAppointmentById(idOfAppointment);
		return new ResponseEntity<>(appointmentById, HttpStatus.OK);
	}

	@PutMapping(UPDATE_APPOINTMENT_STATUS)
	public ResponseEntity<?> updateAppointmentWithStatus(@PathVariable(name = ID) UUID idOfAppointment,
			@PathVariable(name = STATUS) String status) {
		hospitalServiceImpl.updateAppointmentWithStatus(idOfAppointment, status);
		return new ResponseEntity<>(APPOINTMENT_UPDATED_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(DELETE_APPOINTMENT)
	public ResponseEntity<?> deleteAppointment(@PathVariable(name = ID) UUID idOfAppointment) {
		hospitalServiceImpl.deleteAppointment(idOfAppointment);
		return new ResponseEntity<>(APPOINTMENT_DELETED_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

}
