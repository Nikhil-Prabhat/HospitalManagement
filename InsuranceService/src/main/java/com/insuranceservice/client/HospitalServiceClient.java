package com.insuranceservice.client;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.insuranceservice.dto.BillDTO;
import com.insuranceservice.dto.PatientDTO;
import com.insuranceservice.dto.TreatmentHistoryDTO;
import com.insuranceservice.util.Constants;

@FeignClient(url = "${hospital.url}", name = "${hospital.name}")
public interface HospitalServiceClient extends Constants {

	@GetMapping(GET_PATIENT_BY_ID)
	public PatientDTO getPatientById(@RequestHeader(name = AUTHORIZATION) String token, @PathVariable(name = ID) UUID idOfPatient);

	@GetMapping(GET_ALL_TREATMENT_HISTORY_FOR_A_PATIENT)
	public List<TreatmentHistoryDTO> getAllTreatmentHistoriesByPatientId(@RequestHeader(name = AUTHORIZATION) String token, @PathVariable(name = ID) UUID idOfPatient);

	@GetMapping(GET_BILL_BY_PATIENT_ID)
	public BillDTO getBillByPatientId(@RequestHeader(name = AUTHORIZATION) String token, @PathVariable(name = ID) UUID idOfPatient);
}
