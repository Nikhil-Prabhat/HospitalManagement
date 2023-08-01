package com.securityservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.securityservice.dto.AuthRequest;
import com.securityservice.dto.HospitalUserDTO;
import com.securityservice.dto.TokenValidationResponse;
import com.securityservice.dto.UserToken;
import com.securityservice.exception.UserNotFoundException;
import com.securityservice.service.HospitalUserServiceImpl;
import com.securityservice.util.Constants;
import com.securityservice.util.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping(Constants.AUTHAPP)
public class SecurityController implements Constants {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private HospitalUserServiceImpl hospitalUserServiceImpl;

	@PostMapping(SAVE_USER)
	public ResponseEntity<?> saveUser(@RequestBody @Valid HospitalUserDTO hospitalUserDTO) {
		hospitalUserServiceImpl.saveUser(hospitalUserDTO);
		return new ResponseEntity<>(USER_SAVED_SUCCESSFULLY, HttpStatus.CREATED);
	}

	@PostMapping(LOGIN)
	public ResponseEntity<?> loginAndCreateToken(@RequestBody AuthRequest authRequest) throws UserNotFoundException {
		UserDetails hospitalUser = hospitalUserServiceImpl.loadUserByUsername(authRequest.getUsername());
		if (hospitalUser.getPassword().equals(authRequest.getPassword()))
			return new ResponseEntity<>(new UserToken(authRequest.getUsername(), jwtUtil.generateToken(hospitalUser)),
					HttpStatus.CREATED);
		else 
			throw new UserNotFoundException(INCORRECT_CREDENTIALS + authRequest.getUsername());
		}

	@GetMapping(VALIDATE)
	public ResponseEntity<?> validate(@RequestHeader(AUTHORIZATION) String token) {
		token = token.substring(7);
		TokenValidationResponse tokenValidationResponse = new TokenValidationResponse();
		if (jwtUtil.validateToken(token)) {
			tokenValidationResponse.setUsername(jwtUtil.extractUsername(token));
			tokenValidationResponse.setIsValid(Boolean.TRUE);
			List<SimpleGrantedAuthority> roles = jwtUtil.getRolesFromToken(token);
			tokenValidationResponse.setRole(roles.get(0).getAuthority());

		} else
			tokenValidationResponse.setIsValid(Boolean.FALSE);
		return new ResponseEntity<>(tokenValidationResponse, HttpStatus.OK);
	}

}
