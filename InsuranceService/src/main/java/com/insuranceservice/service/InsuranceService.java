package com.insuranceservice.service;

import java.util.List;
import java.util.UUID;

import com.insuranceservice.dto.InsuranceDTO;
import com.insuranceservice.entity.Insurance;

public interface InsuranceService {
	
	public void saveInsurance(InsuranceDTO insuranceDTO);
	
	public List<Insurance> getAllInsuranceDetails();
	
	public Insurance getInsuranceById(UUID idOfInsurance);
	
	public void deleteInsuraceById(UUID idOfInsurance);

}
