package com.hospitalservice.controller;

import java.util.List;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping(Constants.HOSPITAL_APP)
public class HospitalController implements Constants {

	@Autowired
	private HospitalService hospitalServiceImpl;

	@Autowired
	private AuthClient authClient;

	/* CRUD Endpoints Pertaining to Doctor */

	@PostMapping(SAVE_DOCTOR)
	public ResponseEntity<?> saveDoctors(@RequestHeader(name = "Authorization") String token,
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

	@GetMapping(GET_ALL_DOCTOR)
	public ResponseEntity<?> getAllDoctors() {
		List<Doctor> allDoctors = hospitalServiceImpl.getAllDoctors();
		return new ResponseEntity<>(allDoctors, HttpStatus.OK);
	}

	@GetMapping(GET_DOCTOR_BY_ID)
	public ResponseEntity<?> getDoctorById(@PathVariable(name = ID) UUID idOfDoctor) throws RecordNotFound {
		Doctor doctorById = hospitalServiceImpl.getDoctorById(idOfDoctor);
		return new ResponseEntity<>(doctorById, HttpStatus.OK);
	}

	@GetMapping(GET_ALL_PATIENTS_FOR_A_DOCTOR)
	public ResponseEntity<?> getAllPatientsForADoctor(@PathVariable(name = ID) UUID idOfDoctor) throws RecordNotFound {
		List<Patient> allPatientsForADoctor = hospitalServiceImpl.getAllPatientsForADoctor(idOfDoctor);
		return new ResponseEntity<>(allPatientsForADoctor, HttpStatus.OK);
	}

	@GetMapping(GET_ALL_TREATMENT_HISTORY_FOR_A_DOCTOR)
	public ResponseEntity<?> getAllTreatmentHistoriesForADoctor(@PathVariable(name = ID) UUID idOfDoctor)
			throws RecordNotFound {
		List<TreatmentHistory> allTreatmentHistoryForADoctor = hospitalServiceImpl
				.getAllTreatmentHistoryForADoctor(idOfDoctor);
		return new ResponseEntity<>(allTreatmentHistoryForADoctor, HttpStatus.OK);
	}

	@GetMapping(GET_ALL_APPOINTMENTS_FOR_A_DOCTOR)
	public ResponseEntity<?> getAllAppointmentsByDoctorAssignedName(
			@PathVariable(name = DOCTOR_ASSIGNED_NAME) String doctorAssignedName) {
		List<Appointment> allAppointmentsForADoctor = hospitalServiceImpl
				.getAllAppointmentsForADoctor(doctorAssignedName);
		return new ResponseEntity<>(allAppointmentsForADoctor, HttpStatus.OK);
	}

	@PutMapping(UPDATE_DOCTOR)
	public ResponseEntity<?> updateDoctor(@PathVariable(name = ID) UUID idOfDoctor,
			@RequestBody @Valid DoctorDTO doctorDTO) throws RecordNotFound {
		hospitalServiceImpl.updateDoctor(idOfDoctor, doctorDTO);
		return new ResponseEntity<>(DOCTOR_UPDATED_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(DELETE_DOCTOR)
	public ResponseEntity<?> deleteDoctor(@PathVariable(name = ID) UUID idOfDoctor) throws RecordNotFound {
		hospitalServiceImpl.deleteDoctor(idOfDoctor);
		return new ResponseEntity<>(DOCTOR_DELETED_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

	/* CRUD Endpoints Pertaining to Patients */

	@PostMapping(SAVE_PATIENT)
	public ResponseEntity<?> savePatients(@RequestBody @Valid PatientDTO patientDTO) {
		hospitalServiceImpl.savePatient(patientDTO);
		return new ResponseEntity<>(PATIENT_SAVED_SUCCESSFULLY, HttpStatus.CREATED);
	}

	@GetMapping(GET_ALL_PATIENTS)
	public ResponseEntity<?> getAllPatients() {
		List<Patient> allPatients = hospitalServiceImpl.getAllPatients();
		return new ResponseEntity<>(allPatients, HttpStatus.OK);
	}

	@GetMapping(GET_ALL_TREATMENT_HISTORY_FOR_A_PATIENT)
	public ResponseEntity<?> getAllTreatmentHistoriesForAPatient(@PathVariable(name = ID) UUID idOfPatient)
			throws RecordNotFound {
		List<TreatmentHistory> allTreatmentHistoriesByPatientId = hospitalServiceImpl
				.getAllTreatmentHistoriesByPatientId(idOfPatient);
		return new ResponseEntity<>(allTreatmentHistoriesByPatientId, HttpStatus.OK);
	}

	@GetMapping(GET_BILL_BY_PATIENT_ID)
	public ResponseEntity<?> getBillByPatientId(@PathVariable(name = ID) UUID idOfPatient) throws RecordNotFound {
		Bill billByPatientId = hospitalServiceImpl.getBillByPatientId(idOfPatient);
		return new ResponseEntity<>(billByPatientId, HttpStatus.OK);
	}

	@GetMapping(GET_ALL_APPOINTMENTS_FOR_A_PATIENT)
	public ResponseEntity<?> getAllAppointmentsByPatientName(@PathVariable(name = PATIENT_NAME) String patientName) {
		List<Appointment> allAppointmentsForAPatient = hospitalServiceImpl.getAllAppointmentsForAPatient(patientName);
		return new ResponseEntity<>(allAppointmentsForAPatient, HttpStatus.OK);
	}

	@GetMapping(GET_ALL_DOCTORS_FOR_A_PATIENT)
	public ResponseEntity<?> getAllDoctorsForAPatient(@PathVariable(name = ID) UUID idOfPatient) throws RecordNotFound {
		List<Doctor> allDoctorsForAPatient = hospitalServiceImpl.getAllDoctorsForAPatient(idOfPatient);
		return new ResponseEntity<>(allDoctorsForAPatient, HttpStatus.OK);
	}

	@GetMapping(GET_PATIENT_BY_ID)
	public ResponseEntity<?> getPatientById(@PathVariable(name = ID) UUID idOfPatient) throws RecordNotFound {
		Patient patientById = hospitalServiceImpl.getPatientById(idOfPatient);
		return new ResponseEntity<>(patientById, HttpStatus.OK);
	}

	@PutMapping(UPDATE_PATIENT)
	public ResponseEntity<?> updatePatient(@PathVariable(name = ID) UUID idOfPatient,
			@RequestBody @Valid PatientDTO patientDTO) throws RecordNotFound {
		hospitalServiceImpl.updatePatient(idOfPatient, patientDTO);
		return new ResponseEntity<>(PATIENT_UPDATED_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(DELETE_PATIENT)
	public ResponseEntity<?> deletePatient(@PathVariable(name = ID) UUID idOfPatient) throws RecordNotFound {
		hospitalServiceImpl.deletePatient(idOfPatient);
		return new ResponseEntity<>(PATIENT_DELETED_SUCCESFULLY, HttpStatus.ACCEPTED);
	}

	/* Endpoint to assign patient to a doctor */

	@PutMapping(ASSIGN_PATIENT_TO_DOCTOR)
	public ResponseEntity<?> assignPatientToDoctor(@PathVariable(name = DOCTOR_ID) UUID idOfDoctor,
			@PathVariable(name = PATIENT_ID) UUID idOfPatient) throws RecordNotFound {
		hospitalServiceImpl.assignPatientToDoctor(idOfDoctor, idOfPatient);
		return new ResponseEntity<>(ASSIGN_PATIENT_TO_DOCTOR_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

	/* CRUD Endpoints related to appointment */

	@PostMapping(SAVE_APPOINTMENT)
	public ResponseEntity<?> saveAppointment(@RequestBody @Valid AppointmentDTO appointmentDTO) {
		hospitalServiceImpl.saveAppointment(appointmentDTO);
		return new ResponseEntity<>(APPOINTMENT_SAVED_SUCCESSFULLY, HttpStatus.CREATED);
	}

	@GetMapping(GET_ALL_APPOINTMENTS)
	public ResponseEntity<?> getAllAppointments() {
		List<Appointment> allAppointments = hospitalServiceImpl.getAllAppointments();
		return new ResponseEntity<>(allAppointments, HttpStatus.OK);
	}

	@GetMapping(GET_APPOINTMENT_BY_ID)
	public ResponseEntity<?> getAppointmentById(@PathVariable(name = ID) UUID idOfAppointment) throws RecordNotFound {
		Appointment appointmentById = hospitalServiceImpl.getAppointmentById(idOfAppointment);
		return new ResponseEntity<>(appointmentById, HttpStatus.OK);
	}

	@PutMapping(UPDATE_APPOINTMENT_STATUS)
	public ResponseEntity<?> updateAppointmentWithStatus(@PathVariable(name = ID) UUID idOfAppointment,
			@PathVariable(name = STATUS) String status) throws RecordNotFound {
		hospitalServiceImpl.updateAppointmentWithStatus(idOfAppointment, status);
		return new ResponseEntity<>(APPOINTMENT_UPDATED_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(DELETE_APPOINTMENT)
	public ResponseEntity<?> deleteAppointment(@PathVariable(name = ID) UUID idOfAppointment) throws RecordNotFound {
		hospitalServiceImpl.deleteAppointment(idOfAppointment);
		return new ResponseEntity<>(APPOINTMENT_DELETED_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

	/* CRUD Endpoints Pertaining to Treatment History */

	@PostMapping(SAVE_TREATMENT_HISTORY)
	public ResponseEntity<?> saveTreatmentHistory(@RequestBody @Valid TreatmentHistoryDTO treatmentHistoryDTO) {
		hospitalServiceImpl.saveTreatmentHistory(treatmentHistoryDTO);
		return new ResponseEntity<>(TREATMENT_HISTORY_SAVED_SUCCESSFULLY, HttpStatus.CREATED);
	}

	@GetMapping(GET_ALL_TREATMENTS)
	public ResponseEntity<?> getAllTreatmentHistories() {
		List<TreatmentHistory> allTreatmentHistories = hospitalServiceImpl.getAllTreatmentHistories();
		return new ResponseEntity<>(allTreatmentHistories, HttpStatus.OK);
	}

	@GetMapping(GET_TREATMENT_HISTORY_BY_ID)
	public ResponseEntity<?> getTreatmentHistoryById(@PathVariable(name = ID) UUID idOfTreatmentHistory)
			throws RecordNotFound {
		TreatmentHistory treatmentHistoryById = hospitalServiceImpl.getTreatmentHistoryById(idOfTreatmentHistory);
		return new ResponseEntity<>(treatmentHistoryById, HttpStatus.OK);
	}

	@PutMapping(UPDATE_TREATMENT_HISTORY)
	public ResponseEntity<?> updateTreatmentInTreatmentHistory(@PathVariable(name = ID) UUID idOfTreatmentHistory,
			@PathVariable(name = TREATMENT) String treatment) throws RecordNotFound {
		hospitalServiceImpl.updateTreatmentInTreatmentHistory(idOfTreatmentHistory, treatment);
		return new ResponseEntity<>(TREATMENT_UPDATED_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(DELETE_TREATMENT)
	public ResponseEntity<?> deleteTreatment(@PathVariable(name = ID) UUID idOfTreatment) throws RecordNotFound {
		hospitalServiceImpl.deleteTreatment(idOfTreatment);
		return new ResponseEntity<>(TREATMENT_DELETED_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

	/* CRUD Endpoints for Bill */

	@PostMapping(SAVE_BILL)
	public ResponseEntity<?> saveBill(@RequestBody @Valid BillDTO billDTO) {
		hospitalServiceImpl.saveBill(billDTO);
		return new ResponseEntity<>(BILL_SAVED_SUCCESSFULLY, HttpStatus.CREATED);
	}

	@GetMapping(GET_ALL_BILLS)
	public ResponseEntity<?> getAllBills() {
		List<Bill> allBills = hospitalServiceImpl.getAllBills();
		return new ResponseEntity<>(allBills, HttpStatus.OK);
	}

	@GetMapping(GET_BILL_BY_ID)
	public ResponseEntity<?> getBillById(@PathVariable(name = ID) UUID idOfBill) throws RecordNotFound {
		Bill billById = hospitalServiceImpl.getBillById(idOfBill);
		return new ResponseEntity<>(billById, HttpStatus.OK);
	}

	@PutMapping(UPDATE_BILL)
	public ResponseEntity<?> updateBill(@PathVariable(name = ID) UUID idOfBill, @RequestBody BillDTO billDTO)
			throws RecordNotFound {
		hospitalServiceImpl.updateBill(idOfBill, billDTO);
		return new ResponseEntity<>(BILL_UPDATED_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(DELETE_BILL)
	public ResponseEntity<?> deleteBill(@PathVariable(name = ID) UUID idOfBill) throws RecordNotFound {
		hospitalServiceImpl.deleteBill(idOfBill);
		return new ResponseEntity<>(BILL_DELETED_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

	/* Get Total Bill Of Hospital */
	@GetMapping(GET_TOTAL_BILL_OF_HOSPITAL)
	public ResponseEntity<?> getTotalBillOfHospital() {
		double totalBillAmountOfHospital = hospitalServiceImpl.getTotalBillAmountOfHospital();
		return new ResponseEntity<>(totalBillAmountOfHospital, HttpStatus.OK);
	}
}
