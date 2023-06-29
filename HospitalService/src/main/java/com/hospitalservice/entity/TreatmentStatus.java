package com.hospitalservice.entity;

public enum TreatmentStatus {

	NOT_STARTED("NOT_STARTED"), 
	UNDER_TREATMENT("UNDER_TREATMENT"),
	RECOVERED("RECOVERED");

	private String treatmentStatus;

	private TreatmentStatus(String treatmentStatus) {
		this.setTreatmentStatus(treatmentStatus);
	}

	public String getTreatmentStatus() {
		return treatmentStatus;
	}

	public void setTreatmentStatus(String treatmentStatus) {
		this.treatmentStatus = treatmentStatus;
	}

}
