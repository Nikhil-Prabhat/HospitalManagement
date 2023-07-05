package com.hospitalservice.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospitalservice.util.Constants;

@Aspect
@Component
public class HospitalServiceLoggingAdvice implements Constants {

	Logger LOG = LoggerFactory.getLogger(HospitalServiceLoggingAdvice.class);

	@Pointcut(value = "execution(* com.hospitalservice.controller.*.*(..) ) || execution(* com.hospitalservice.service.*.*(..) )")
	public void hospitalServicePointcut() {
	}
	
	@Around("hospitalServicePointcut()")
	public Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = proceedingJoinPoint.getSignature().getName();
		String className = proceedingJoinPoint.getTarget().getClass().getName();
		Object[] array = proceedingJoinPoint.getArgs();

		LOG.info("[ " + METHOD_INVOKED + className + " : " + methodName + "(), " + ARGUMENTS
				+ mapper.writeValueAsString(array) + " ]");

		// It is used to get the result after the method is executed
		Object object = proceedingJoinPoint.proceed();

		LOG.info("[ " + METHOD_INVOKED + className + " : " + methodName + "(), " + RESPONSE
				+ mapper.writeValueAsString(object) + " ]");
		return object;
	}
}
