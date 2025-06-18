package com.santt4na.health_check.controller.docs;

import com.santt4na.health_check.dto.doctorDTO.DoctorRequestDTO;
import com.santt4na.health_check.dto.patientDTO.PatientRequestDTO;
import com.santt4na.health_check.dto.securityDTO.AccountCredentialsDTO;
import com.santt4na.health_check.dto.securityDTO.TokenDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface AuthControllerDocs {
	
	@Operation(
		summary = "Authenticates a user and returns a token",
		description = "Validates user credentials and generates an access token for authentication.",
		tags = {"Authentication"},
		responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		}
	)
	ResponseEntity<?> signin(AccountCredentialsDTO credentials);
	
	@Operation(
		summary = "Refresh token for authenticated user and returns a token",
		description = "Generates a new access token using the provided refresh token and username.",
		tags = {"Authentication"},
		responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		}
	)
	ResponseEntity<?> refreshToken(
		String username,
		String refreshToken);
	
	@Operation(
		summary = "Create a new Doctor",
		description = "Registers a new Doctor in the system with the provided credentials.",
		tags = {"User Management"},
		responses = {
			@ApiResponse(description = "Created", responseCode = "201", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		}
	)
	ResponseEntity<TokenDTO> registerDoctor(AccountCredentialsDTO user, DoctorRequestDTO doctor);
	
	@Operation(
		summary = "Create a new Patient",
		description = "Registers a new Patient in the system with the provided credentials.",
		tags = {"User Management"},
		responses = {
			@ApiResponse(description = "Created", responseCode = "201", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		}
	)
	AccountCredentialsDTO registerPatient(AccountCredentialsDTO credentials, PatientRequestDTO patient);
}
