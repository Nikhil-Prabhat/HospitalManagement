package com.insuranceservice.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insuranceservice.dto.BillDTO;
import com.insuranceservice.dto.InsuranceDTO;
import com.insuranceservice.dto.PatientClaimDTO;
import com.insuranceservice.dto.PatientDTO;
import com.insuranceservice.dto.TreatmentHistoryDTO;
import com.insuranceservice.entity.Insurance;
import com.insuranceservice.entity.PatientClaim;
import com.insuranceservice.exception.InsurerNotFoundException;
import com.insuranceservice.exception.PatientClaimNotFoundException;
import com.insuranceservice.service.InsuranceService;
import com.insuranceservice.util.Constants;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;

@RestController
@RequestMapping(Constants.INSURANCEAPP)
public class InsuranceController implements Constants {

	@Autowired
	private InsuranceService insuranceServiceImpl;

	/* CRUD Endpoints Pertaining to Insurance */

	@PostMapping(SAVE_INSURER_DETAILS)
	public ResponseEntity<?> saveInsurerDetails(@RequestBody @Valid InsuranceDTO insuranceDTO) {
		insuranceServiceImpl.saveInsurance(insuranceDTO);
		return new ResponseEntity<>(INSURER_SAVED_SUCCESSFULLY, HttpStatus.CREATED);
	}

	@GetMapping(GET_ALL_INSURER_DETAILS)
	public ResponseEntity<?> getAllInsurerDetails() {
		List<Insurance> allInsuranceDetails = insuranceServiceImpl.getAllInsuranceDetails();
		return new ResponseEntity<>(allInsuranceDetails, HttpStatus.OK);
	}

	@GetMapping(GET_INSURER_BY_ID)
	public ResponseEntity<?> getInsurerDetailsById(@PathVariable(ID) UUID idOfInsurer) throws InsurerNotFoundException {
		Insurance insuranceById = insuranceServiceImpl.getInsuranceById(idOfInsurer);
		return new ResponseEntity<>(insuranceById, HttpStatus.OK);
	}

	@DeleteMapping(DELETE_INSURER_BY_ID)
	public ResponseEntity<?> deleteInsurerById(@PathVariable(ID) UUID idOfInsurer) throws InsurerNotFoundException {
		insuranceServiceImpl.deleteInsuraceById(idOfInsurer);
		return new ResponseEntity<>(INSURER_DELETED_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

	/* CRUD Endpoints pertaining to Patient Claim */

	@PostMapping(SAVE_PATIENT_CLAIM)
	public ResponseEntity<?> savePatientClaim(@RequestBody @Valid PatientClaimDTO patientClaimDTO) {
		insuranceServiceImpl.savePatientClaim(patientClaimDTO);
		return new ResponseEntity<>(PATIENT_CLAIM_SAVED_SUCCESSFULLY, HttpStatus.CREATED);
	}

	@GetMapping(GET_ALL_PATIENT_CLAIMS)
	public ResponseEntity<?> getAllPatientClaimDetails() {
		List<PatientClaim> allPatientClaims = insuranceServiceImpl.getAllPatientClaims();
		return new ResponseEntity<>(allPatientClaims, HttpStatus.OK);
	}

	@GetMapping(GET_PATIENT_CLAIM_BY_ID)
	public ResponseEntity<?> getPatientClaimById(@PathVariable(ID) UUID idOfPatientClaim)
			throws PatientClaimNotFoundException {
		PatientClaim patientClaimById = insuranceServiceImpl.getPatientClaimById(idOfPatientClaim);
		return new ResponseEntity<>(patientClaimById, HttpStatus.OK);
	}

	@PutMapping(UPDATE_PATIENT_CLAIM)
	public ResponseEntity<?> updatePatientClaim(@PathVariable(ID) UUID idOfPatientClaim,
			@RequestBody @Valid PatientClaimDTO patientClaimDTO) throws PatientClaimNotFoundException {
		insuranceServiceImpl.updatePatientClaim(idOfPatientClaim, patientClaimDTO);
		return new ResponseEntity<>(PATIENT_CLAIM_UPDATED_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(DELETE_PATIENT_CLAIM_BY_ID)
	public ResponseEntity<?> deletePatientClaim(@PathVariable(ID) UUID idOfPatientClaim)
			throws InsurerNotFoundException {
		insuranceServiceImpl.deleteInsuraceById(idOfPatientClaim);
		return new ResponseEntity<>(PATIENT_CLAIM_DELETED_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

	/* Client Related Endpoints */

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_ALL_TREATMENT_HISTORY_FOR_A_PATIENT)
	public ResponseEntity<?> getAllTreatmentHistoriesByPatientId(@PathVariable(name = ID) UUID idOfPatient) {
		List<TreatmentHistoryDTO> allTreatmentHistoriesByPatientId = insuranceServiceImpl
				.getAllTreatmentHistoriesByPatientId(idOfPatient);
		return new ResponseEntity<>(allTreatmentHistoriesByPatientId, HttpStatus.OK);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_PATIENT_BY_ID)
	public ResponseEntity<?> getPatientDetailsByPatientId(@PathVariable(name = ID) UUID idOfPatient) {
		PatientDTO patientById = insuranceServiceImpl.getPatientById(idOfPatient);
		return new ResponseEntity<>(patientById, HttpStatus.OK);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_BILL_BY_PATIENT_ID)
	public ResponseEntity<?> getBillDetailsByPatientId(@PathVariable(name = ID) UUID idOfPatient) {
		BillDTO billByPatientId = insuranceServiceImpl.getBillByPatientId(idOfPatient);
		return new ResponseEntity<>(billByPatientId, HttpStatus.OK);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(FINAL_COST_CALCULATION)
	public ResponseEntity<?> calculateRemainingAmountToBePaid(@PathVariable(PATIENT_ID) UUID idOfPatient,
			@PathVariable(INSURANCE_ID) UUID idOfInsurance) throws InsurerNotFoundException {
		Double finalAmountToBePaid = insuranceServiceImpl.calculateAmountToBePaidByPatient(idOfPatient, idOfInsurance);
		return new ResponseEntity<>(finalAmountToBePaid, HttpStatus.OK);
	}

	/* Fallback method for circuit breaker and retry */
	public ResponseEntity<?> insuranceFallbackMethod(Exception ex) {
		return new ResponseEntity<>(FALLBACK_METHOD_MESSAGE, HttpStatus.EXPECTATION_FAILED);
	}

}
