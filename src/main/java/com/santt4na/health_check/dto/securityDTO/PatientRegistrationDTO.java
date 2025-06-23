package com.santt4na.health_check.dto.securityDTO;

import com.santt4na.health_check.dto.patientDTO.PatientRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientRegistrationDTO {
	
	private AccountCredentialsDTO user;
	
	private PatientRequestDTO patient;
	
}
