package com.airpro.common.exceptionhandler;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiException {

	private HttpStatus status;
	private String msg;
	Map<String, String> errorMsg;
	 
}
