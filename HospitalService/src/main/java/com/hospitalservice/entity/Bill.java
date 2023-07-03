package com.hospitalservice.entity;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BILL_TABLE", schema = "HOSPITAL")
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "PATIENT_BILL_FK" ,referencedColumnName = "ID")
	private Patient patient;

	private Double consultationFee;
	private Double pharmacyFee;
	private Double hospitalizationFee;

	@Setter(value = AccessLevel.NONE)
	private Double totalAmountOfBill;
}
