package com.santt4na.health_check.dto.securityDTO;

import com.santt4na.health_check.dto.doctorDTO.DoctorRequestDTO;
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
public class DoctorRegistrationDTO {
	
	@NotNull(message = "User data is required")
	@Valid
	private AccountCredentialsDTO user;
	
	@NotNull(message = "Doctor data is required")
	@Valid
	private DoctorRequestDTO doctor;
}