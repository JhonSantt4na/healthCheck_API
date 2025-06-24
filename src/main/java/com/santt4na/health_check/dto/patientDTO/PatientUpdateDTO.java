package com.santt4na.health_check.dto.patientDTO;

import com.santt4na.health_check.enums.Gender;
import javax.validation.constraints.*;
import java.time.LocalDate;

public record PatientUpdateDTO(
	
	@Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
	@NotNull(message = "Full name is required")
	String fullName,
	
	@Email(message = "Email must be valid")
	@NotNull(message = "Email is required")
	String email,
	
	@NotNull(message = "Gender is required")
	Gender gender,
	
	@Pattern(
		regexp = "\\+?\\d{2}\\s?\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}",
		message = "Phone must follow the pattern: +55 (11) 91234-5678"
	)
	@NotNull(message = "Phone is required")
	String phone,
	
	@Past(message = "Date of birth must be a past date")
	@NotNull(message = "Date of birth is required")
	LocalDate dateOfBirth,
	
	@Pattern(
		regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}",
		message = "CPF must follow the pattern: 000.000.000-00"
	)
	@NotNull(message = "CPF is required")
	String cpf,
	
	@Size(max = 50, message = "Health insurance name must be at most 50 characters")
	String healthInsurance
) {}

