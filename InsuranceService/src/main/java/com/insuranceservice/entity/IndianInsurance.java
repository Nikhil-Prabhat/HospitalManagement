package com.insuranceservice.entity;

import com.insuranceservice.util.Constants;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue(Constants.INDIAN)
@Data
@EqualsAndHashCode(callSuper = false)
public class IndianInsurance extends Insurance {
	
	private String insurerOriginState;

}
