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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insuranceservice.dto.InsuranceDTO;
import com.insuranceservice.entity.Insurance;
import com.insuranceservice.service.InsuranceService;
import com.insuranceservice.util.Constants;

@RestController
@RequestMapping(Constants.INSURANCEAPP)
public class InsuranceController implements Constants {

	@Autowired
	private InsuranceService insuranceServiceImpl;

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

}
