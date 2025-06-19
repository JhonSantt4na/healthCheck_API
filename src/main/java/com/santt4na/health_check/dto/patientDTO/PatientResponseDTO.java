package com.santt4na.health_check.dto.patientDTO;

import com.santt4na.health_check.enums.Gender;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record PatientResponseDTO(
	
	Long id,
	String fullName,
	String email,
	Gender gender,
	String phone,
	LocalDate dateOfBirth,
	String cpf,
	String healthInsurance,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) implements Serializable {}