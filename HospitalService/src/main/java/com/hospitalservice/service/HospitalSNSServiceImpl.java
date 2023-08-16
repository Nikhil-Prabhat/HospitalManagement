package com.hospitalservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;

@Service
public class HospitalSNSServiceImpl implements HospitalSNSService{
	
	@Autowired
	private AmazonSNSClient amazonSNSClient;
	
	@Value("${aws.topic-arn}")
	private String topicArn;

	@Override
	public void publishMessageToTopic(String message, String mailHeader) {
		PublishRequest publishRequest = new PublishRequest(topicArn, message, mailHeader);
		amazonSNSClient.publish(publishRequest);
	}

}
