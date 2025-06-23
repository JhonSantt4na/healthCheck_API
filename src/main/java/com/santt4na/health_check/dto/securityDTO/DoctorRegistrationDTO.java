package com.santt4na.health_check.dto.securityDTO;

import com.santt4na.health_check.dto.doctorDTO.DoctorRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRegistrationDTO {
	
	private AccountCredentialsDTO user;
	private DoctorRequestDTO doctor;
}