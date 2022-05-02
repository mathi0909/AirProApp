package com.airpro.common.exceptionhandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class AirProExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiException> businessExceptionHandler(BusinessException ex){
		log.info("Enter businessExceptionHandler");
		ApiException apiException = new ApiException(ex.getStatus(), ex.getErrorMsg(),null );
		
		return new ResponseEntity<ApiException>(apiException, new HttpHeaders(),apiException.getStatus());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiException>  handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    ApiException apiException = new ApiException(HttpStatus.BAD_REQUEST, "Invalid request",errors);
	    return new ResponseEntity<ApiException>(apiException, new HttpHeaders(),apiException.getStatus());
	}
	
}
