package com.santt4na.health_check.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException() {
		super("Object not found by Id!");
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
}