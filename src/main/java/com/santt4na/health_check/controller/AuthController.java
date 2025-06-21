package com.santt4na.health_check.controller;

import com.santt4na.health_check.controller.docs.AuthControllerDocs;
import com.santt4na.health_check.dto.doctorDTO.DoctorRequestDTO;
import com.santt4na.health_check.dto.patientDTO.PatientRequestDTO;
import com.santt4na.health_check.dto.securityDTO.AccountCredentialsDTO;
import com.santt4na.health_check.dto.securityDTO.TokenDTO;
import com.santt4na.health_check.service.security.AuthService;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController implements AuthControllerDocs {
	
	@Autowired
	private AuthService service;
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@PostMapping("/signin")
	@Override
	public ResponseEntity<?> signin(@RequestBody AccountCredentialsDTO credentials) {
		if (credentialsIsInvalid(credentials))return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		var token = service.signIn(credentials);
		
		if (token == null) ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		return  ResponseEntity.ok().body(token);
	}
	
	@PutMapping("/refresh/{username}")
	@Override
	public ResponseEntity<?> refreshToken(
		@PathVariable("username") String username,
		@RequestHeader("Authorization") String refreshToken) {
		if (parametersAreInvalid(username, refreshToken)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		var token = service.refreshToken(username, refreshToken);
		if (token == null) ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		return  ResponseEntity.ok().body(token);
	}
	
	@PostMapping(value = "/registerDoctor",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<TokenDTO> registerDoctor(@Valid @RequestBody AccountCredentialsDTO user, DoctorRequestDTO doctor) {
		return service.registerDoctor(user, doctor);
	}
	
	@PostMapping(value = "/registerPatient",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public AccountCredentialsDTO registerPatient(AccountCredentialsDTO credentials, PatientRequestDTO patient) {
		return service.registerPatient(credentials, patient);
	}
	
	private boolean parametersAreInvalid(String username, String refreshToken) {
		return StringUtils.isBlank(username) || StringUtils.isBlank(refreshToken);
	}
	
	private static boolean credentialsIsInvalid(AccountCredentialsDTO credentials) {
		return credentials == null ||
			StringUtils.isBlank(credentials.getPassword()) ||
			StringUtils.isBlank(credentials.getUserName());
	}
}