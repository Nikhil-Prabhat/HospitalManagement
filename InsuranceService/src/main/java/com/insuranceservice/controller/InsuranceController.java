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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.insuranceservice.client.AuthClient;
import com.insuranceservice.dto.BillDTO;
import com.insuranceservice.dto.InsuranceDTO;
import com.insuranceservice.dto.PatientClaimDTO;
import com.insuranceservice.dto.PatientDTO;
import com.insuranceservice.dto.TokenValidationResponse;
import com.insuranceservice.dto.TreatmentHistoryDTO;
import com.insuranceservice.entity.Insurance;
import com.insuranceservice.entity.PatientClaim;
import com.insuranceservice.exception.InsurerNotFoundException;
import com.insuranceservice.exception.PatientClaimNotFoundException;
import com.insuranceservice.service.InsuranceService;
import com.insuranceservice.util.Constants;
import com.insuranceservice.util.Roles;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;

@RestController
@RequestMapping(Constants.INSURANCEAPP)
public class InsuranceController implements Constants {

	@Autowired
	private InsuranceService insuranceServiceImpl;

	@Autowired
	private AuthClient authClient;

	/* CRUD Endpoints Pertaining to Insurance */

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@PostMapping(SAVE_INSURER_DETAILS)
	public ResponseEntity<?> saveInsurerDetails(@RequestHeader(name = AUTHORIZATION) String token,
			@RequestBody @Valid InsuranceDTO insuranceDTO) {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole()))) {
			insuranceServiceImpl.saveInsurance(insuranceDTO);
			return new ResponseEntity<>(INSURER_SAVED_SUCCESSFULLY, HttpStatus.CREATED);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_ALL_INSURER_DETAILS)
	public ResponseEntity<?> getAllInsurerDetails(@RequestHeader(name = AUTHORIZATION) String token) {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			List<Insurance> allInsuranceDetails = insuranceServiceImpl.getAllInsuranceDetails();
			return new ResponseEntity<>(allInsuranceDetails, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_INSURER_BY_ID)
	public ResponseEntity<?> getInsurerDetailsById(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(ID) UUID idOfInsurer) throws InsurerNotFoundException {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			Insurance insuranceById = insuranceServiceImpl.getInsuranceById(idOfInsurer);
			return new ResponseEntity<>(insuranceById, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@DeleteMapping(DELETE_INSURER_BY_ID)
	public ResponseEntity<?> deleteInsurerById(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(ID) UUID idOfInsurer) throws InsurerNotFoundException {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())) {
			insuranceServiceImpl.deleteInsuraceById(idOfInsurer);
			return new ResponseEntity<>(INSURER_DELETED_SUCCESSFULLY, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	/* CRUD Endpoints pertaining to Patient Claim */

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@PostMapping(SAVE_PATIENT_CLAIM)
	public ResponseEntity<?> savePatientClaim(@RequestHeader(name = AUTHORIZATION) String token,
			@RequestBody @Valid PatientClaimDTO patientClaimDTO) {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			insuranceServiceImpl.savePatientClaim(patientClaimDTO);
			return new ResponseEntity<>(PATIENT_CLAIM_SAVED_SUCCESSFULLY, HttpStatus.CREATED);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_ALL_PATIENT_CLAIMS)
	public ResponseEntity<?> getAllPatientClaimDetails(@RequestHeader(name = AUTHORIZATION) String token) {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())) {
			List<PatientClaim> allPatientClaims = insuranceServiceImpl.getAllPatientClaims();
			return new ResponseEntity<>(allPatientClaims, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_PATIENT_CLAIM_BY_ID)
	public ResponseEntity<?> getPatientClaimById(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(ID) UUID idOfPatientClaim) throws PatientClaimNotFoundException {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			PatientClaim patientClaimById = insuranceServiceImpl.getPatientClaimById(idOfPatientClaim);
			return new ResponseEntity<>(patientClaimById, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@PutMapping(UPDATE_PATIENT_CLAIM)
	public ResponseEntity<?> updatePatientClaim(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(ID) UUID idOfPatientClaim, @RequestBody @Valid PatientClaimDTO patientClaimDTO)
			throws PatientClaimNotFoundException {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			insuranceServiceImpl.updatePatientClaim(idOfPatientClaim, patientClaimDTO);
			return new ResponseEntity<>(PATIENT_CLAIM_UPDATED_SUCCESSFULLY, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@DeleteMapping(DELETE_PATIENT_CLAIM_BY_ID)
	public ResponseEntity<?> deletePatientClaim(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(ID) UUID idOfPatientClaim) throws InsurerNotFoundException {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())) {
			insuranceServiceImpl.deleteInsuraceById(idOfPatientClaim);
			return new ResponseEntity<>(PATIENT_CLAIM_DELETED_SUCCESSFULLY, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	/* Client Related Endpoints */

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_ALL_TREATMENT_HISTORY_FOR_A_PATIENT)
	public ResponseEntity<?> getAllTreatmentHistoriesByPatientId(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfPatient) {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			List<TreatmentHistoryDTO> allTreatmentHistoriesByPatientId = insuranceServiceImpl
					.getAllTreatmentHistoriesByPatientId(token, idOfPatient);
			return new ResponseEntity<>(allTreatmentHistoriesByPatientId, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_PATIENT_BY_ID)
	public ResponseEntity<?> getPatientDetailsByPatientId(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfPatient) {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			PatientDTO patientById = insuranceServiceImpl.getPatientById(token, idOfPatient);
			return new ResponseEntity<>(patientById, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(GET_BILL_BY_PATIENT_ID)
	public ResponseEntity<?> getBillDetailsByPatientId(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(name = ID) UUID idOfPatient) {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			BillDTO billByPatientId = insuranceServiceImpl.getBillByPatientId(token, idOfPatient);
			return new ResponseEntity<>(billByPatientId, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = FALLBACK_METHOD_NAME)
	@Retry(name = RETRY_NAME)
	@GetMapping(FINAL_COST_CALCULATION)
	public ResponseEntity<?> calculateRemainingAmountToBePaid(@RequestHeader(name = AUTHORIZATION) String token,
			@PathVariable(PATIENT_ID) UUID idOfPatient, @PathVariable(INSURANCE_ID) UUID idOfInsurance)
			throws InsurerNotFoundException {
		TokenValidationResponse tokenValidationResponse = authClient.verifyToken(token);

		if (tokenValidationResponse.getIsValid()
				&& (tokenValidationResponse.getRole().equals(Roles.ROLE_ADMIN.getUserRole())
						|| tokenValidationResponse.getRole().equals(Roles.ROLE_USER.getUserRole()))) {
			Double finalAmountToBePaid = insuranceServiceImpl.calculateAmountToBePaidByPatient(token, idOfPatient,
					idOfInsurance);
			return new ResponseEntity<>(finalAmountToBePaid, HttpStatus.OK);
		} else
			return new ResponseEntity<>(FORBIDDEN_ROLE_MSG, HttpStatus.FORBIDDEN);
	}

	/* Fallback method for circuit breaker and retry */
	public ResponseEntity<?> insuranceFallbackMethod(Exception ex) {
		return new ResponseEntity<>(FALLBACK_METHOD_MESSAGE, HttpStatus.EXPECTATION_FAILED);
	}

}
