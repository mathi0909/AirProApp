package com.airpro.common.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class AirProExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiException> businessExceptionHandler(BusinessException ex){
		log.info("Enter businessExceptionHandler");
		ApiException apiException = new ApiException(ex.getStatus(), ex.getErrorMsg() );
		
		return new ResponseEntity<ApiException>(apiException, new HttpHeaders(),apiException.getStatus());
	}
	
}
