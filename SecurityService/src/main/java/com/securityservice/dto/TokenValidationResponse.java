package com.securityservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenValidationResponse {
	
	private String username;
	private String role;
	private Boolean isValid;

}
