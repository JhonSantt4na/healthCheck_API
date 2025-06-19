package com.santt4na.health_check.exception.handler;

import com.santt4na.health_check.dto.ErrorDTO;
import com.santt4na.health_check.exception.BusinessException;
import com.santt4na.health_check.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
			.map(error -> error.getField() + ": " + error.getDefaultMessage())
			.collect(Collectors.toList());
		
		ErrorDTO errorDTO = new ErrorDTO(
			"VALIDATION_ERROR",
			String.join(", ", errors),
			LocalDateTime.now()
		);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDTO> handleResourceNotFound(ResourceNotFoundException ex) {
		ErrorDTO errorDTO = new ErrorDTO(
			"RESOURCE_NOT_FOUND",
			ex.getMessage(),
			LocalDateTime.now()
		);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorDTO> handleBusinessException(BusinessException ex) {
		ErrorDTO errorDTO = new ErrorDTO(
			"BUSINESS_ERROR",
			ex.getMessage(),
			LocalDateTime.now()
		);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
	}
}