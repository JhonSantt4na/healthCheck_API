package com.santt4na.health_check.exception.handler;
/*
import com.santt4na.health_check.exception.DuplicateEmailException;
import com.santt4na.health_check.exception.InvalidJwtAuthenticationException;
import com.santt4na.health_check.exception.RequiredObjectIsNullException;
import com.santt4na.health_check.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomEntityResponseHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(
			new Date(),
			ex.getMessage(),
			request.getDescription(false)
		);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DuplicateEmailException.class)
	public final ResponseEntity<ExceptionResponse> handleDuplicateEmailExceptions(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(
			new Date(),
			ex.getMessage(),
			request.getDescription(false)
		);
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(InvalidJwtAuthenticationException.class)
	public final ResponseEntity<ExceptionResponse> handleInvalidJwtAuthenticationExceptions(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(
			new Date(),
			ex.getMessage(),
			request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(RequiredObjectIsNullException.class)
	public final ResponseEntity<ExceptionResponse> handleRequiredObjectExceptions(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(
			new Date(),
			ex.getMessage(),
			request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleResourceNotFoundExceptions(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(
			new Date(),
			ex.getMessage(),
			request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BusinessException.class)
	public final ResponseEntity<ExceptionResponse> handleBusinessException(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(
			new Date(),
			ex.getMessage(),
			request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
}
*/