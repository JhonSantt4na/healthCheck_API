package com.santt4na.health_check.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {
	
	public BusinessException() {
		super("A business rule has been violated.");
	}
	public BusinessException(String message) {
		super(message);
	}
}
