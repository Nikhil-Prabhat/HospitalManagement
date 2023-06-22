package com.securityservice.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.securityservice.exception.UserNotFoundException;
import com.securityservice.util.Constants;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@RestControllerAdvice
public class SecurityServiceExceptionAdvice implements Constants {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}
	
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler({ UsernameNotFoundException.class })
	public Map<String, String> handleUserNameNotFoundException(UsernameNotFoundException ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put(ERROR_MESSAGE, ex.getMessage());
		errorMap.put(ERROR_CLASS, ex.getClass().getName());
		return errorMap;
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler({ UserNotFoundException.class })
	public Map<String, String> handleUserNotFoundException(UserNotFoundException ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put(ERROR_MESSAGE, ex.getMessage());
		errorMap.put(ERROR_CLASS, ex.getClass().getName());
		return errorMap;
	}

}
