package com.santt4na.health_check.dto.patientDTO;

import com.santt4na.health_check.enums.Gender;
import javax.validation.constraints.*;
import java.time.LocalDate;

public record PatientUpdateDTO(
	
	@Size(min = 2, max = 100) String fullName,
	@Email  String email,
	Gender gender,
	@Pattern(regexp = "\\+?\\d{2}\\s?\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}") String phone,
	@Past LocalDate dateOfBirth,
	@Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}") String cpf,
	@Size(max = 50) String healthInsurance
) {}

