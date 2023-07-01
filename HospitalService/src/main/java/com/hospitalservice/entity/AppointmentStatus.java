package com.hospitalservice.entity;

public enum AppointmentStatus {
	
	CONFIRMED("CONFIRMED"),
	NOT_ACCEPTED("NOT_ACCEPTED");
	
	private String appointmentStatus;
	
	private AppointmentStatus(String appointmentStatus) {
		this.setAppointmentStatus(appointmentStatus);
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

}
