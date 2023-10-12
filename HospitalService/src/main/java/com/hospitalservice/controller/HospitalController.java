package com.hospitalservice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitalservice.client.AuthClient;
import com.hospitalservice.dto.AppointmentDTO;
import com.hospitalservice.dto.BillDTO;
import com.hospitalservice.dto.DoctorDTO;
import com.hospitalservice.dto.PatientDTO;
import com.hospitalservice.dto.TokenValidationResponse;
import com.hospitalservice.dto.TreatmentHistoryDTO;
import com.hospitalservice.entity.Appointment;
import com.hospitalservice.entity.Bill;
import com.hospitalservice.entity.Doctor;
import com.hospitalservice.entity.Patient;
import com.hospitalservice.entity.TreatmentHistory;
import com.hospitalservice.exception.RecordNotFound;
import com.hospitalservice.service.HospitalService;
import com.hospitalservice.util.Constants;
import com.hospitalservice.util.Roles;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;

@RestController
@RequestMapping(Constants.HOSPITAL_APP)
@CrossOrigin(origins = "*")
public class HospitalController implements Constants {

	@Autowired
	private HospitalService hospitalServiceImpl;

	@Autowired
	private AuthClient authClient;

	/* CRUD Endpoints Pertaining to Doctor */

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@PostMapping(SAVE_DOCTOR)
	public ResponseEntity<?> saveDoctors(@RequestHeader(name = AUTHORIZATION) String token,
			@RequestBody @Valid DoctorDTO doctorDTO) {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_DOCTOR.getUserRole()))) {
			hospitalServiceImpl.saveDoctor(doctorDTO);
			return new ResponseEntity<>(DOCTOR_SAVED_SUCCESSFULLY, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
		}

	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_ALL_DOCTOR)
	public ResponseEntity<?> getAllDoctors(@RequestHeader(name = AUTHORIZATION) String token) {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_DOCTOR.getUserRole()))) {
			List<Doctor> allDoctors = hospitalServiceImpl.getAllDoctors();
			return new ResponseEntity<>(allDoctors, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_DOCTOR_BY_ID)
	public ResponseEntity<?> getDoctorById(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfDoctor) throws RecordNotFound {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_DOCTOR.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			Doctor doctorById = hospitalServiceImpl.getDoctorById(idOfDoctor);
			return new ResponseEntity<>(doctorById, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_ALL_PATIENTS_FOR_A_DOCTOR)
	public ResponseEntity<?> getAllPatientsForADoctor(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfDoctor) throws RecordNotFound {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_DOCTOR.getUserRole()))) {
			List<Patient> allPatientsForADoctor = hospitalServiceImpl.getAllPatientsForADoctor(idOfDoctor);
			return new ResponseEntity<>(allPatientsForADoctor, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_ALL_TREATMENT_HISTORY_FOR_A_DOCTOR)
	public ResponseEntity<?> getAllTreatmentHistoriesForADoctor(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfDoctor) throws RecordNotFound {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_DOCTOR.getUserRole()))) {
			List<TreatmentHistory> allTreatmentHistoryForADoctor = hospitalServiceImpl
					.getAllTreatmentHistoryForADoctor(idOfDoctor);
			return new ResponseEntity<>(allTreatmentHistoryForADoctor, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_ALL_APPOINTMENTS_FOR_A_DOCTOR)
	public ResponseEntity<?> getAllAppointmentsByDoctorAssignedName(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = DOCTOR_ASSIGNED_NAME) String doctorAssignedName) {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_DOCTOR.getUserRole()))) {
			List<Appointment> allAppointmentsForADoctor = hospitalServiceImpl
					.getAllAppointmentsForADoctor(doctorAssignedName);
			return new ResponseEntity<>(allAppointmentsForADoctor, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@PutMapping(UPDATE_DOCTOR)
	public ResponseEntity<?> updateDoctor(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfDoctor, @RequestBody @Valid DoctorDTO doctorDTO) throws RecordNotFound {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_DOCTOR.getUserRole()))) {
			hospitalServiceImpl.updateDoctor(idOfDoctor, doctorDTO);
			return new ResponseEntity<>(DOCTOR_UPDATED_SUCCESSFULLY, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@DeleteMapping(DELETE_DOCTOR)
	public ResponseEntity<?> deleteDoctor(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfDoctor) throws RecordNotFound {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole()))) {
			hospitalServiceImpl.deleteDoctor(idOfDoctor);
			return new ResponseEntity<>(DOCTOR_DELETED_SUCCESSFULLY, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	/* CRUD Endpoints Pertaining to Patients */

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@PostMapping(SAVE_PATIENT)
	public ResponseEntity<?> savePatients(@RequestHeader(name = AUTHORIZATION) String token,
			@RequestBody @Valid PatientDTO patientDTO) {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			hospitalServiceImpl.savePatient(patientDTO);
			return new ResponseEntity<>(PATIENT_SAVED_SUCCESSFULLY, HttpStatus.CREATED);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_ALL_PATIENTS)
	public ResponseEntity<?> getAllPatients(@RequestHeader(name = AUTHORIZATION) String token) {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			List<Patient> allPatients = hospitalServiceImpl.getAllPatients();
			return new ResponseEntity<>(allPatients, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_ALL_TREATMENT_HISTORY_FOR_A_PATIENT)
	public ResponseEntity<?> getAllTreatmentHistoriesForAPatient(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfPatient) throws RecordNotFound {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			List<TreatmentHistory> allTreatmentHistoriesByPatientId = hospitalServiceImpl
					.getAllTreatmentHistoriesByPatientId(idOfPatient);
			return new ResponseEntity<>(allTreatmentHistoriesByPatientId, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	// @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod =
	// FALLBACK_METHOD_NAME)
	// @Retry(name = RETRY_NAME)
	@GetMapping(GET_BILL_BY_PATIENT_ID)
	public ResponseEntity<?> getBillByPatientId(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfPatient) throws RecordNotFound {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			Bill billByPatientId = hospitalServiceImpl.getBillByPatientId(idOfPatient);
			return new ResponseEntity<>(billByPatientId, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_ALL_APPOINTMENTS_FOR_A_PATIENT)
	public ResponseEntity<?> getAllAppointmentsByPatientName(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = PATIENT_NAME) String patientName) {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			List<Appointment> allAppointmentsForAPatient = hospitalServiceImpl
					.getAllAppointmentsForAPatient(patientName);
			return new ResponseEntity<>(allAppointmentsForAPatient, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_ALL_DOCTORS_FOR_A_PATIENT)
	public ResponseEntity<?> getAllDoctorsForAPatient(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfPatient) throws RecordNotFound {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			List<Doctor> allDoctorsForAPatient = hospitalServiceImpl.getAllDoctorsForAPatient(idOfPatient);
			return new ResponseEntity<>(allDoctorsForAPatient, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_PATIENT_BY_ID)
	public ResponseEntity<?> getPatientById(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfPatient) throws RecordNotFound {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_DOCTOR.getUserRole()))) {
			Patient patientById = hospitalServiceImpl.getPatientById(idOfPatient);
			return new ResponseEntity<>(patientById, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@PutMapping(UPDATE_PATIENT)
	public ResponseEntity<?> updatePatient(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfPatient, @RequestBody @Valid PatientDTO patientDTO)
			throws RecordNotFound {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			hospitalServiceImpl.updatePatient(idOfPatient, patientDTO);
			return new ResponseEntity<>(PATIENT_UPDATED_SUCCESSFULLY, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@DeleteMapping(DELETE_PATIENT)
	public ResponseEntity<?> deletePatient(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfPatient) throws RecordNotFound {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole()))) {
			hospitalServiceImpl.deletePatient(idOfPatient);
			return new ResponseEntity<>(PATIENT_DELETED_SUCCESFULLY, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	/* Endpoint to assign patient to a doctor */

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@PutMapping(ASSIGN_PATIENT_TO_DOCTOR)
	public ResponseEntity<?> assignPatientToDoctor(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = DOCTOR_ID) UUID idOfDoctor, @PathVariable(name = PATIENT_ID) UUID idOfPatient)
			throws RecordNotFound {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole()))) {
			hospitalServiceImpl.assignPatientToDoctor(idOfDoctor, idOfPatient);
			return new ResponseEntity<>(ASSIGN_PATIENT_TO_DOCTOR_SUCCESSFULLY, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	/* CRUD Endpoints related to appointment */

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@PostMapping(SAVE_APPOINTMENT)
	public ResponseEntity<?> saveAppointment(@RequestHeader(name = AUTHORIZATION) String token,
			@RequestBody @Valid AppointmentDTO appointmentDTO) {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole()))) {
			hospitalServiceImpl.saveAppointment(appointmentDTO);
			return new ResponseEntity<>(APPOINTMENT_SAVED_SUCCESSFULLY, HttpStatus.CREATED);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_ALL_APPOINTMENTS)
	public ResponseEntity<?> getAllAppointments(@RequestHeader(name = AUTHORIZATION) String token) {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole()))) {
			List<Appointment> allAppointments = hospitalServiceImpl.getAllAppointments();
			return new ResponseEntity<>(allAppointments, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_APPOINTMENT_BY_ID)
	public ResponseEntity<?> getAppointmentById(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfAppointment) throws RecordNotFound {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			Appointment appointmentById = hospitalServiceImpl.getAppointmentById(idOfAppointment);
			return new ResponseEntity<>(appointmentById, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);

	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@PutMapping(UPDATE_APPOINTMENT_STATUS)
	public ResponseEntity<?> updateAppointmentWithStatus(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfAppointment, @PathVariable(name = STATUS) String status)
			throws RecordNotFound {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			hospitalServiceImpl.updateAppointmentWithStatus(idOfAppointment, status);
			return new ResponseEntity<>(APPOINTMENT_UPDATED_SUCCESSFULLY, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@DeleteMapping(DELETE_APPOINTMENT)
	public ResponseEntity<?> deleteAppointment(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfAppointment) throws RecordNotFound {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole()))) {
			hospitalServiceImpl.deleteAppointment(idOfAppointment);
			return new ResponseEntity<>(APPOINTMENT_DELETED_SUCCESSFULLY, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	/* CRUD Endpoints Pertaining to Treatment History */

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@PostMapping(SAVE_TREATMENT_HISTORY)
	public ResponseEntity<?> saveTreatmentHistory(@RequestHeader(name = AUTHORIZATION) String token,
			@RequestBody @Valid TreatmentHistoryDTO treatmentHistoryDTO) {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_DOCTOR.getUserRole()))) {
			hospitalServiceImpl.saveTreatmentHistory(treatmentHistoryDTO);
			return new ResponseEntity<>(TREATMENT_HISTORY_SAVED_SUCCESSFULLY, HttpStatus.CREATED);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_ALL_TREATMENTS)
	public ResponseEntity<?> getAllTreatmentHistories(@RequestHeader(name = AUTHORIZATION) String token) {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole()))) {
			List<TreatmentHistory> allTreatmentHistories = hospitalServiceImpl.getAllTreatmentHistories();
			return new ResponseEntity<>(allTreatmentHistories, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_TREATMENT_HISTORY_BY_ID)
	public ResponseEntity<?> getTreatmentHistoryById(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfTreatmentHistory) throws RecordNotFound {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_DOCTOR.getUserRole()))) {
			TreatmentHistory treatmentHistoryById = hospitalServiceImpl.getTreatmentHistoryById(idOfTreatmentHistory);
			return new ResponseEntity<>(treatmentHistoryById, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@PutMapping(UPDATE_TREATMENT_HISTORY)
	public ResponseEntity<?> updateTreatmentInTreatmentHistory(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfTreatmentHistory, @PathVariable(name = TREATMENT) String treatment)
			throws RecordNotFound {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_DOCTOR.getUserRole()))) {
			hospitalServiceImpl.updateTreatmentInTreatmentHistory(idOfTreatmentHistory, treatment);
			return new ResponseEntity<>(TREATMENT_UPDATED_SUCCESSFULLY, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@DeleteMapping(DELETE_TREATMENT)
	public ResponseEntity<?> deleteTreatment(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfTreatment) throws RecordNotFound {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole()))) {
			hospitalServiceImpl.deleteTreatment(idOfTreatment);
			return new ResponseEntity<>(TREATMENT_DELETED_SUCCESSFULLY, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	/* CRUD Endpoints for Bill */

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@PostMapping(SAVE_BILL)
	public ResponseEntity<?> saveBill(@RequestHeader(name = AUTHORIZATION) String token,
			@RequestBody @Valid BillDTO billDTO) {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			hospitalServiceImpl.saveBill(billDTO);
			return new ResponseEntity<>(BILL_SAVED_SUCCESSFULLY, HttpStatus.CREATED);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_ALL_BILLS)
	public ResponseEntity<?> getAllBills(@RequestHeader(name = AUTHORIZATION) String token) {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole()))) {
			List<Bill> allBills = hospitalServiceImpl.getAllBills();
			return new ResponseEntity<>(allBills, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_BILL_BY_ID)
	public ResponseEntity<?> getBillById(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfBill) throws RecordNotFound {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			Bill billById = hospitalServiceImpl.getBillById(idOfBill);
			return new ResponseEntity<>(billById, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@PutMapping(UPDATE_BILL)
	public ResponseEntity<?> updateBill(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfBill, @RequestBody BillDTO billDTO) throws RecordNotFound {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole()))) {
			hospitalServiceImpl.updateBill(idOfBill, billDTO);
			return new ResponseEntity<>(BILL_UPDATED_SUCCESSFULLY, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@DeleteMapping(DELETE_BILL)
	public ResponseEntity<?> deleteBill(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfBill) throws RecordNotFound {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole()))) {
			hospitalServiceImpl.deleteBill(idOfBill);
			return new ResponseEntity<>(BILL_DELETED_SUCCESSFULLY, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	/* Get Total Bill Of Hospital */
	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_TOTAL_BILL_OF_HOSPITAL)
	public ResponseEntity<?> getTotalBillOfHospital(@RequestHeader(name = AUTHORIZATION) String token) {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			double totalBillAmountOfHospital = hospitalServiceImpl.getTotalBillAmountOfHospital();
			return new ResponseEntity<>(totalBillAmountOfHospital, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	/* Fallback method for circuit breaker and retry */
	public ResponseEntity<?> hospitalFallbackMethod(Exception ex) {
		return new ResponseEntity<>(FALLBACK_METHOD_MESSAGE, HttpStatus.EXPECTATION_FAILED);
	}

}
