package com.santt4na.health_check.controller;

import com.santt4na.health_check.controller.docs.AuthControllerDocs;
import com.santt4na.health_check.dto.securityDTO.AccountCredentialsDTO;
import com.santt4na.health_check.dto.securityDTO.DoctorRegistrationDTO;
import com.santt4na.health_check.dto.securityDTO.PatientRegistrationDTO;
import com.santt4na.health_check.service.security.AuthService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthControllerDocs {
	
	private final AuthService service;
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@PostMapping("/signin")
	@Override
	public ResponseEntity<?> signin( @Valid @RequestBody AccountCredentialsDTO credentials) {
		
		if (credentialsIsInvalid(credentials))return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		var token = service.signIn(credentials);
		
		if (token == null) ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		return  ResponseEntity.ok().body(token);
	}
	
	@PutMapping("/refresh/{username}")
	@Override
	public ResponseEntity<?> refreshToken(@PathVariable("username") String username, @RequestHeader("Authorization") String refreshToken) {
		
		if (parametersAreInvalid(username, refreshToken)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		var token = service.refreshToken(username, refreshToken);
		if (token == null) ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		return  ResponseEntity.ok().body(token);
	}
	
	private boolean parametersAreInvalid(String username, String refreshToken) {
		return StringUtils.isBlank(username) || StringUtils.isBlank(refreshToken);
	}
	
	private static boolean credentialsIsInvalid(AccountCredentialsDTO credentials) {
		
		return credentials == null ||
			StringUtils.isBlank(credentials.getPassword()) ||
			StringUtils.isBlank(credentials.getUserName());
	}
	
	@PostMapping(value = "/register/patient",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<?> registerPatient(@Valid @RequestBody PatientRegistrationDTO registrationDTO) {
		try {
			Map<String, Object>  response = service.registerPatient(registrationDTO.getUser(), registrationDTO.getPatient());
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("System configuration error: " + e.getMessage());
		}
	}
	
	@PostMapping(value = "/register/doctor",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<?> registerDoctor(@Valid @RequestBody DoctorRegistrationDTO registrationDTO) {
		try {
			Map<String, Object>  response = service.registerDoctor(registrationDTO.getUser(), registrationDTO.getDoctor());
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("System configuration error: " + e.getMessage());
		}
	}
	
}