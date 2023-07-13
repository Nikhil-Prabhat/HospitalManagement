package com.insuranceservice.service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insuranceservice.client.HospitalServiceClient;
import com.insuranceservice.dto.BillDTO;
import com.insuranceservice.dto.InsuranceDTO;
import com.insuranceservice.dto.PatientClaimDTO;
import com.insuranceservice.dto.PatientDTO;
import com.insuranceservice.dto.TreatmentHistoryDTO;
import com.insuranceservice.entity.IndianInsurance;
import com.insuranceservice.entity.Insurance;
import com.insuranceservice.entity.InternationInsurance;
import com.insuranceservice.entity.PatientClaim;
import com.insuranceservice.repository.InsuranceRepository;
import com.insuranceservice.repository.PatientClaimRepository;
import com.insuranceservice.util.Constants;

@Service
public class InsuranceServiceImpl implements InsuranceService, Constants {

	@Autowired
	private InsuranceRepository insuranceRepository;

	@Autowired
	private PatientClaimRepository patientClaimRepository;

	@Autowired
	private HospitalServiceClient hospitalServiceClient;

	@Override
	public void saveInsurance(InsuranceDTO insuranceDTO) {
		if (insuranceDTO.getInsuranceType().equals(INDIAN)) {
			IndianInsurance indianInsurance = new IndianInsurance();
			indianInsurance.setInsurerOriginState(insuranceDTO.getInsurerOriginState());
			setCommonPropertiesAndSaveEntity(indianInsurance, insuranceDTO);
		} else {
			InternationInsurance internationInsurance = new InternationInsurance();
			internationInsurance.setInsurerOriginCountry(insuranceDTO.getInsurerOriginCountry());
			setCommonPropertiesAndSaveEntity(internationInsurance, insuranceDTO);
		}

	}

	@Override
	public List<Insurance> getAllInsuranceDetails() {
		List<Insurance> allInsuranceDetails = insuranceRepository.findAll();
		return allInsuranceDetails;
	}

	@Override
	public Insurance getInsuranceById(UUID idOfInsurance) {
		Insurance insuranceById = insuranceRepository.findById(idOfInsurance).orElse(null);
		return insuranceById;
	}

	@Override
	public void deleteInsuraceById(UUID idOfInsurance) {
		insuranceRepository.deleteById(idOfInsurance);
	}

	private void setCommonPropertiesAndSaveEntity(Insurance insurance, InsuranceDTO insuranceDTO) {
		insurance.setInsurerName(insuranceDTO.getInsurerName());
		insurance.setInsurerAmountLimit(insuranceDTO.getInsurerAmountLimit());
		insurance.setDisbursementTime(insuranceDTO.getDisbursementTime());

		insuranceRepository.save(insurance);
	}

	@Override
	public void savePatientClaim(PatientClaimDTO patientClaimDTO) {
		PatientClaim patientClaim = new PatientClaim();
		patientClaim.setPatientId(patientClaimDTO.getPatientId());
		patientClaim.setInsuranceTaken(patientClaimDTO.getInsuranceTaken());
		patientClaim.setAmountSpent(patientClaimDTO.getAmountSpent());
		patientClaim.setRemainingAmount(patientClaimDTO.getRemainingAmount());

		patientClaimRepository.save(patientClaim);

	}

	@Override
	public List<PatientClaim> getAllPatientClaims() {
		return patientClaimRepository.findAll();
	}

	@Override
	public PatientClaim getPatientClaimById(UUID idOfPatientClaim) {
		PatientClaim patientClaimById = patientClaimRepository.findById(idOfPatientClaim).orElse(null);
		return patientClaimById;
	}

	@Override
	public void updatePatientClaim(UUID idOfPatientClaim, PatientClaimDTO patientClaimDTO) {
		PatientClaim patientClaimById = getPatientClaimById(idOfPatientClaim);

		if (Objects.nonNull(patientClaimById)) {
			patientClaimById.setAmountSpent(patientClaimDTO.getAmountSpent());
			patientClaimById.setRemainingAmount(patientClaimDTO.getRemainingAmount());
			
			patientClaimRepository.save(patientClaimById);
		}

	}

	@Override
	public void deletePatientClaimById(UUID idOfPatientClaim) {
		patientClaimRepository.deleteById(idOfPatientClaim);

	}

	@Override
	public PatientDTO getPatientById(UUID idOfPatient) {
		PatientDTO patientById = hospitalServiceClient.getPatientById(idOfPatient);
		return patientById;
	}

	@Override
	public List<TreatmentHistoryDTO> getAllTreatmentHistoriesByPatientId(UUID idOfPatient) {
		List<TreatmentHistoryDTO> allTreatmentHistoriesByPatientId = hospitalServiceClient
				.getAllTreatmentHistoriesByPatientId(idOfPatient);
		return allTreatmentHistoriesByPatientId;
	}

	@Override
	public BillDTO getBillByPatientId(UUID idOfPatient) {
		BillDTO billByPatientId = hospitalServiceClient.getBillByPatientId(idOfPatient);
		return billByPatientId;
	}

	@Override
	public Double calculateAmountToBePaidByPatient(UUID idOfPatient, UUID idOfInsurance) {
		BillDTO billByPatientId = getBillByPatientId(idOfPatient);
		Insurance insuranceById = getInsuranceById(idOfInsurance);
		Double billAmount = Objects.nonNull(billByPatientId.getTotalAmountOfBill()) ? billByPatientId.getTotalAmountOfBill() : 0.0d;
		
		return insuranceById.getInsurerAmountLimit() - billAmount;
	}

	
	

}
