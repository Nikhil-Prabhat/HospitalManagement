package com.insuranceservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insuranceservice.dto.InsuranceDTO;
import com.insuranceservice.entity.IndianInsurance;
import com.insuranceservice.entity.Insurance;
import com.insuranceservice.entity.InternationInsurance;
import com.insuranceservice.repository.InsuranceRepository;
import com.insuranceservice.util.Constants;

@Service
public class InsuranceServiceImpl implements InsuranceService, Constants {

	@Autowired
	private InsuranceRepository insuranceRepository;

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

}
