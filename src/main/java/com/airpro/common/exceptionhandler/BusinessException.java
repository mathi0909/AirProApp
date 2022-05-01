package com.airpro.common.exceptionhandler;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.Data;

 
@Data
public class BusinessException extends SystemException{
	
	private String errorMsg;
	private HttpStatus status;
	 
	Environment env;

	public BusinessException() {
		super();
		this.errorMsg = "Something went wrong";
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}
	
	public BusinessException(String msg) {
		super();
		this.errorMsg = msg;
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}
	
	public BusinessException(String msg,HttpStatus status) {
		super(msg);
		this.errorMsg = msg;
		this.status = status;
	}
}


