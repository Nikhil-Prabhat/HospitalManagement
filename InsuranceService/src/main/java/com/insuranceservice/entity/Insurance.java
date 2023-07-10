package com.insuranceservice.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "INSURER_TABLE", schema = "INSURANCE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "INSURANCE_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class Insurance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String insurerName;
	private Double insurerAmountLimit;
	private Integer disbursementTime;
	
	@Column(name = "INSURANCE_TYPE" , insertable = false, updatable = false)
	@Setter(value = AccessLevel.NONE)
	private String insuranceType;

}
