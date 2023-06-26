package com.hospitalservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.hospitalservice.dto.TokenValidationResponse;
import com.hospitalservice.util.Constants;

@FeignClient(url = "${auth.url}", name = "${auth.name}")
public interface AuthClient extends Constants {

	@GetMapping(VALIDATE)
	public TokenValidationResponse verifyToken(@RequestHeader(name = AUTHORIZATION, required = true) String token);

}
