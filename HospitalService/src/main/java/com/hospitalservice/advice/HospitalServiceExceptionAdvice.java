package com.hospitalservice.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hospitalservice.exception.RecordNotFound;
import com.hospitalservice.util.Constants;

@RestControllerAdvice
public class HospitalServiceExceptionAdvice implements Constants {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public Map<String, String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put(ERROR_MESSAGE, ex.getMessage());
		errorMap.put(ERROR_CLASS, ex.getClass().getName());
		return errorMap;
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ RecordNotFound.class })
	public Map<String, String> handleRecordNotFound(RecordNotFound ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put(ERROR_MESSAGE, ex.getMessage());
		errorMap.put(ERROR_CLASS, ex.getClass().getName());
		return errorMap;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class })
	public Map<String, String> handleGenericException(Exception ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put(ERROR_MESSAGE, ex.getMessage());
		errorMap.put(ERROR_CLASS, ex.getClass().getName());
		return errorMap;
	}

}
