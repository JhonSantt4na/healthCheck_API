package com.santt4na.health_check.exception.handler;

import com.santt4na.health_check.dto.ErrorDTO;
import com.santt4na.health_check.exception.BusinessException;
import com.santt4na.health_check.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	private ResponseEntity<ErrorDTO> buildResponseEntity(String code, List<String> messages, HttpStatus status) {
		ErrorDTO errorDTO = new ErrorDTO(code, messages, LocalDateTime.now());
		return ResponseEntity.status(status).body(errorDTO);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
		logger.warn("Validation failed: {}", ex.getMessage());
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
			.map(FieldError::getDefaultMessage)
			.collect(Collectors.toList());
		return buildResponseEntity("VALIDATION_ERROR", errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDTO> handleResourceNotFound(ResourceNotFoundException ex) {
		logger.info("Resource not found: {}", ex.getMessage());
		return buildResponseEntity("RESOURCE_NOT_FOUND", List.of(ex.getMessage()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorDTO> handleBusinessException(BusinessException ex) {
		logger.warn("Business exception: {}", ex.getMessage());
		return buildResponseEntity("BUSINESS_ERROR", List.of(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDTO> handleGenericException(Exception ex) {
		logger.error("Unexpected error", ex);
		return buildResponseEntity(
			"INTERNAL_SERVER_ERROR",
			List.of("An unexpected error occurred. Please try again later."),
			HttpStatus.INTERNAL_SERVER_ERROR
		);
	}
}