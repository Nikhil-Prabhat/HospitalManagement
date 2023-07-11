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
import com.insuranceservice.service.InsuranceService;
import com.insuranceservice.util.Constants;

@RestController
@RequestMapping(Constants.INSURANCEAPP)
public class InsuranceController implements Constants {

	@Autowired
	private InsuranceService insuranceServiceImpl;

	/* CRUD Endpoints Pertaining to Insurance */

	@PostMapping(SAVE_INSURER_DETAILS)
	public ResponseEntity<?> saveInsurerDetails(@RequestBody InsuranceDTO insuranceDTO) {
		insuranceServiceImpl.saveInsurance(insuranceDTO);
		return new ResponseEntity<>(INSURER_SAVED_SUCCESSFULLY, HttpStatus.CREATED);
	}

	@GetMapping(GET_ALL_INSURER_DETAILS)
	public ResponseEntity<?> getAllInsurerDetails() {
		List<Insurance> allInsuranceDetails = insuranceServiceImpl.getAllInsuranceDetails();
		return new ResponseEntity<>(allInsuranceDetails, HttpStatus.OK);
	}

	@GetMapping(GET_INSURER_BY_ID)
	public ResponseEntity<?> getInsurerDetailsById(@PathVariable(ID) UUID idOfInsurer) {
		Insurance insuranceById = insuranceServiceImpl.getInsuranceById(idOfInsurer);
		return new ResponseEntity<>(insuranceById, HttpStatus.OK);
	}

	@DeleteMapping(DELETE_INSURER_BY_ID)
	public ResponseEntity<?> deleteInsurerById(@PathVariable(ID) UUID idOfInsurer) {
		insuranceServiceImpl.deleteInsuraceById(idOfInsurer);
		return new ResponseEntity<>(INSURER_DELETED_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

	/* CRUD Endpoints pertaining to Patient Claim */

	@PostMapping(SAVE_PATIENT_CLAIM)
	public ResponseEntity<?> savePatientClaim(@RequestBody PatientClaimDTO patientClaimDTO) {
		insuranceServiceImpl.savePatientClaim(patientClaimDTO);
		return new ResponseEntity<>(PATIENT_CLAIM_SAVED_SUCCESSFULLY, HttpStatus.CREATED);
	}

	@GetMapping(GET_ALL_PATIENT_CLAIMS)
	public ResponseEntity<?> getAllPatientClaimDetails() {
		List<PatientClaim> allPatientClaims = insuranceServiceImpl.getAllPatientClaims();
		return new ResponseEntity<>(allPatientClaims, HttpStatus.OK);
	}

	@GetMapping(GET_PATIENT_CLAIM_BY_ID)
	public ResponseEntity<?> getPatientClaimById(@PathVariable(ID) UUID idOfPatientClaim) {
		PatientClaim patientClaimById = insuranceServiceImpl.getPatientClaimById(idOfPatientClaim);
		return new ResponseEntity<>(patientClaimById, HttpStatus.OK);
	}

	@PutMapping(UPDATE_PATIENT_CLAIM)
	public ResponseEntity<?> updatePatientClaim(@PathVariable(ID) UUID idOfPatientClaim,
			@RequestBody PatientClaimDTO patientClaimDTO) {
		insuranceServiceImpl.updatePatientClaim(idOfPatientClaim, patientClaimDTO);
		return new ResponseEntity<>(PATIENT_CLAIM_UPDATED_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(DELETE_PATIENT_CLAIM_BY_ID)
	public ResponseEntity<?> deletePatientClaim(@PathVariable(ID) UUID idOfPatientClaim) {
		insuranceServiceImpl.deleteInsuraceById(idOfPatientClaim);
		return new ResponseEntity<>(PATIENT_CLAIM_DELETED_SUCCESSFULLY, HttpStatus.ACCEPTED);
	}

	/* Client Related Endpoints */
	
	@GetMapping(GET_ALL_TREATMENT_HISTORY_FOR_A_PATIENT)
	public ResponseEntity<?> getAllTreatmentHistoriesByPatientId(@PathVariable(name = ID) UUID idOfPatient) {
		List<TreatmentHistoryDTO> allTreatmentHistoriesByPatientId = insuranceServiceImpl
				.getAllTreatmentHistoriesByPatientId(idOfPatient);
		return new ResponseEntity<>(allTreatmentHistoriesByPatientId, HttpStatus.OK);
	}

	@GetMapping(GET_PATIENT_BY_ID)
	public ResponseEntity<?> getPatientDetailsByPatientId(@PathVariable(name = ID) UUID idOfPatient) {
		PatientDTO patientById = insuranceServiceImpl.getPatientById(idOfPatient);
		return new ResponseEntity<>(patientById, HttpStatus.OK);
	}

	@GetMapping(GET_BILL_BY_PATIENT_ID)
	public ResponseEntity<?> getBillDetailsByPatientId(@PathVariable(name = ID) UUID idOfPatient) {
		BillDTO billByPatientId = insuranceServiceImpl.getBillByPatientId(idOfPatient);
		return new ResponseEntity<>(billByPatientId, HttpStatus.OK);
	}
	
	@GetMapping(FINAL_COST_CALCULATION)
	public ResponseEntity<?> calculateRemainingAmountToBePaid(@PathVariable(TOTAL_EXPENSE) Double totalExpense, @PathVariable(INSURANCE_AMOUNT) Double insuranceAmount) {
		Double finalAmountToBePaid = insuranceServiceImpl.calculateAmountToBePaidByPatient(totalExpense, insuranceAmount);
		return new ResponseEntity<>(finalAmountToBePaid, HttpStatus.OK);
	}

}
