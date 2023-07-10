package com.insuranceservice.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("INDIAN")
@Data
@EqualsAndHashCode(callSuper = false)
public class IndianInsurance extends Insurance {
	
	private String insurerOriginState;

}
