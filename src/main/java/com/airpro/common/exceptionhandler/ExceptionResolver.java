package com.airpro.common.exceptionhandler;

public class ExceptionResolver {

	public static SystemException resolve(Exception ex) {
		if (ex instanceof BusinessException)
			return (BusinessException) ex;
		else
			return new SystemException(ex);
	}
}
