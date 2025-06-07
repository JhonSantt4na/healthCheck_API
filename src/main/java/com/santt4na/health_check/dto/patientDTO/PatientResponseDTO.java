package com.santt4na.health_check.dto.patientDTO;

import com.santt4na.health_check.enums.Gender;
import com.santt4na.health_check.enums.UserState;

import java.time.LocalDate;

public record PatientResponseDTO(
	Long id,
	String name,
	String email,
	LocalDate dateOfBirth,
	String cpf,
	String phone,
	String healthInsurance,
	Gender gender,
	UserState status
) {
}
