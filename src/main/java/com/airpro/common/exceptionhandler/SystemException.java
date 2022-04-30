package com.airpro.common.exceptionhandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SystemException extends Exception{
	
	public SystemException() {
		super();
	}
	
	public SystemException(String msg) {
		super(msg);
	}
	
	public SystemException(Throwable throwable) {
		super(throwable);
		log.error(throwable.getMessage());
	}
	
	

}
