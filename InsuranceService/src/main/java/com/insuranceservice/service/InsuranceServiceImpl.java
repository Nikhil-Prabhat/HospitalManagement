package com.insuranceservice.service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
import com.insuranceservice.exception.InsurerNotFoundException;
import com.insuranceservice.exception.PatientClaimNotFoundException;
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
	public Insurance getInsuranceById(UUID idOfInsurance) throws InsurerNotFoundException {
		Insurance insuranceById = insuranceRepository.findById(idOfInsurance).orElse(null);

		if (Objects.nonNull(insuranceById))
			return insuranceById;
		else
			throw new InsurerNotFoundException(INSURER_NOT_FOUND_WITH_ID + idOfInsurance);
	}

	@Override
	public void deleteInsuraceById(UUID idOfInsurance) throws InsurerNotFoundException {
		Insurance insuranceById = getInsuranceById(idOfInsurance);

		if (Objects.nonNull(insuranceById))
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
	public PatientClaim getPatientClaimById(UUID idOfPatientClaim) throws PatientClaimNotFoundException {
		PatientClaim patientClaimById = patientClaimRepository.findById(idOfPatientClaim).orElse(null);

		if (Objects.nonNull(patientClaimById))
			return patientClaimById;
		else
			throw new PatientClaimNotFoundException(PATIENT_CLAIM_NOT_FOUND_WITH_ID + idOfPatientClaim);
	}

	@Override
	public void updatePatientClaim(UUID idOfPatientClaim, PatientClaimDTO patientClaimDTO)
			throws PatientClaimNotFoundException {
		PatientClaim patientClaimById = getPatientClaimById(idOfPatientClaim);

		if (Objects.nonNull(patientClaimById)) {
			patientClaimById.setAmountSpent(patientClaimDTO.getAmountSpent());
			patientClaimById.setRemainingAmount(patientClaimDTO.getRemainingAmount());

			patientClaimRepository.save(patientClaimById);
		}

	}

	@Override
	public void deletePatientClaimById(UUID idOfPatientClaim) throws PatientClaimNotFoundException {
		PatientClaim patientClaimById = getPatientClaimById(idOfPatientClaim);

		if (Objects.nonNull(patientClaimById))
			patientClaimRepository.deleteById(idOfPatientClaim);

	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = KEY_PATIENT_DETAILS_BY_PATIENT_ID)
	public PatientDTO getPatientById(String token, UUID idOfPatient) {
		PatientDTO patientById = hospitalServiceClient.getPatientById(token, idOfPatient);
		return patientById;
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = KEY_TREATMENT_HISTORIES_BY_PATIENT_ID)
	public List<TreatmentHistoryDTO> getAllTreatmentHistoriesByPatientId(String token, UUID idOfPatient) {
		List<TreatmentHistoryDTO> allTreatmentHistoriesByPatientId = hospitalServiceClient
				.getAllTreatmentHistoriesByPatientId(token, idOfPatient);
		return allTreatmentHistoriesByPatientId;
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = KEY_BILL_DETAILS_BY_PATIENT_ID)
	public BillDTO getBillByPatientId(String token, UUID idOfPatient) {
		BillDTO billByPatientId = hospitalServiceClient.getBillByPatientId(token, idOfPatient);
		return billByPatientId;
	}

	@Override
	public Double calculateAmountToBePaidByPatient(String token, UUID idOfPatient, UUID idOfInsurance)
			throws InsurerNotFoundException {
		BillDTO billByPatientId = getBillByPatientId(token, idOfPatient);
		Insurance insuranceById = getInsuranceById(idOfInsurance);
		Double billAmount = Objects.nonNull(billByPatientId.getTotalAmountOfBill())
				? billByPatientId.getTotalAmountOfBill()
				: 0.0d;

		return insuranceById.getInsurerAmountLimit() - billAmount;
	}
	
	

}
