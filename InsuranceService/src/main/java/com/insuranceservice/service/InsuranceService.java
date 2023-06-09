package com.insuranceservice.service;

import java.util.List;
import java.util.UUID;

import com.insuranceservice.dto.BillDTO;
import com.insuranceservice.dto.InsuranceDTO;
import com.insuranceservice.dto.PatientClaimDTO;
import com.insuranceservice.dto.PatientDTO;
import com.insuranceservice.dto.TreatmentHistoryDTO;
import com.insuranceservice.entity.Insurance;
import com.insuranceservice.entity.PatientClaim;
import com.insuranceservice.exception.InsurerNotFoundException;
import com.insuranceservice.exception.PatientClaimNotFoundException;

public interface InsuranceService {

	public void saveInsurance(InsuranceDTO insuranceDTO);

	public List<Insurance> getAllInsuranceDetails();

	public Insurance getInsuranceById(UUID idOfInsurance) throws InsurerNotFoundException;

	public void deleteInsuraceById(UUID idOfInsurance) throws InsurerNotFoundException;

	public void savePatientClaim(PatientClaimDTO patientClaimDTO);

	public List<PatientClaim> getAllPatientClaims();

	public PatientClaim getPatientClaimById(UUID idOfPatientClaim) throws PatientClaimNotFoundException;

	public void updatePatientClaim(UUID idOfPatientClaim, PatientClaimDTO patientDTO) throws PatientClaimNotFoundException;

	public void deletePatientClaimById(UUID idOfPatientClaim) throws PatientClaimNotFoundException;
	
	public PatientDTO getPatientById(UUID idOfPatient);
	
	public List<TreatmentHistoryDTO> getAllTreatmentHistoriesByPatientId(UUID idOfPatient);
	
	public BillDTO getBillByPatientId(UUID idOfPatient);
	
	public Double calculateAmountToBePaidByPatient(UUID idOfPatient, UUID idOfInsurance) throws InsurerNotFoundException;
	

}
