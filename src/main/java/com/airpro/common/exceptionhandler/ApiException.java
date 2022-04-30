package com.airpro.common.exceptionhandler;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiException {

	private HttpStatus status;
	private String msg;
	 
}
