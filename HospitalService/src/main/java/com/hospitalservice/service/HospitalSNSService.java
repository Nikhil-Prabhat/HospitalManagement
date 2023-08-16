package com.hospitalservice.service;

public interface HospitalSNSService {
	
	public void publishMessageToTopic(String message, String mailHeader);

}
