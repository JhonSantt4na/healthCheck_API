package com.santt4na.health_check.dto.securityDTO;

import com.santt4na.health_check.dto.patientDTO.PatientRequestDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientRegistrationDTO {
	
	@NotNull(message = "User data is required")
	@Valid
	private AccountCredentialsDTO user;
	
	@NotNull(message = "Patient data is required")
	@Valid
	private PatientRequestDTO patient;
}