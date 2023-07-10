package com.insuranceservice.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("INTERNATIONAL")
@Data
@EqualsAndHashCode(callSuper = false)
public class InternationInsurance extends Insurance {
	
	private String insurerOriginCountry;

}
